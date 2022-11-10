/* SYSC4001A Assignment 2
 * Cade Theriault
 * 101192252
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pcb.h"
#include "node.h"

#define TIME_QUANTUM (int) 3

int main(int argc, char** argv) {

	int system_run = 1; // boolean variable for system loop

	// Time counters
	int system_time = 0; 
	int curr_exec_time = 0; 
	int process_counter = 0;

	if (argc < 3) {
		printf("Failed to simulate without 3 arguments.\n");
		printf("Missing either: input file, output file, and/or scheduling mode.\n");
		return 1;
	}

	// Grab input and output file names
	char* i_file = argv[1];
	char* o_file = argv[2];
	int sched = atoi(argv[3]); // 0 FCFS, 1 Priority, 2 RR with q = 100
	
	// Clear the output file before adding text to it
	FILE* output_file = fopen(o_file, "w");
	fclose(output_file);
	
	output_file = fopen(o_file, "a");
	if (sched == 1) {
		fprintf(output_file, "Time of Transition,PID,Priority,Old State,New State\n");
	} else {
		fprintf(output_file, "Time of Transition,PID,Old State,New State\n");
	}
	fclose(output_file);
	
	// Create a PCB list
	linkedlist_t *pcb_llist;
	pcb_llist = new_linkedlist();
	
	// Create a ready queue
	linkedlist_t *ready_llist = new_linkedlist();
	
	// Create a executing queue
	linkedlist_t *exec_llist = new_linkedlist();

	// Create a waiting queue
	linkedlist_t *wait_llist = new_linkedlist();

	// Create a terminated list for metrics
	linkedlist_t *terminated_llist = new_linkedlist();

	// Open and read csv file, and write each process into the PCB list
	int load_result = load_csv_as_pcb(i_file, &pcb_llist);
	
	// Clock loop
	while (system_run) {
		
		// if execution_time >= total_cpu_time
		// then running->terminated
		if ( 	exec_llist->head != NULL && 
				(curr_exec_time + exec_llist->head->data->exec_time) >= exec_llist->head->data->total_cpu_time 
			) {
			node_t* new_term_node = get_and_remove_node(&exec_llist);
			append_node(&terminated_llist, new_term_node, sched);
			new_term_node->data->state = TERMINATED_STATE;
			new_term_node->data->term_time = system_time;
			string_pcb(o_file, new_term_node->data, system_time, EXEC_STATE);
			process_counter +=1;

		}

		// if RR preemption occurs
		// then running->ready
		if (sched == 2 && exec_llist->head != NULL && curr_exec_time >= TIME_QUANTUM){
			node_t* new_ready_node = get_and_remove_node(&exec_llist);
			append_node(&ready_llist, new_ready_node, sched);
			new_ready_node->data->state = READY_STATE;
			new_ready_node->data->exec_time += curr_exec_time;
			string_pcb(o_file, new_ready_node->data, system_time, EXEC_STATE);
		}

		// if system_time >= arrival_time
		// then new->ready
		while ( pcb_llist->head != NULL && 
				system_time >= pcb_llist->head->data->arrival_time ) {
			node_t* new_ready_node = get_and_remove_node(&pcb_llist);
			append_node(&ready_llist, new_ready_node, sched);
			new_ready_node->data->state = READY_STATE;
			string_pcb(o_file, new_ready_node->data, system_time, NEW_STATE);
		}

		// if  wait_time >= io_duration
		// then waiting->ready
		while ( wait_llist->head != NULL && 
				( system_time - wait_llist->head->data->wait_time) >= wait_llist->head->data->io_duration 
			) {
			node_t* new_ready_node = get_and_remove_node(&wait_llist);
			append_node(&ready_llist, new_ready_node, sched);
			new_ready_node->data->state = READY_STATE;
			string_pcb(o_file, new_ready_node->data, system_time, WAITING_STATE);

		}

		

		// if execution_time >=IO_frequency
		// then running->waiting
		if ( 	exec_llist->head != NULL && 
				curr_exec_time >= exec_llist->head->data->io_frequency 
			) {
			node_t* new_wait_node = get_and_remove_node(&exec_llist);
			append_node(&wait_llist, new_wait_node, sched);
			new_wait_node->data->state = WAITING_STATE;
			new_wait_node->data->exec_time += curr_exec_time;
			new_wait_node->data->wait_time = system_time;
			string_pcb(o_file, new_wait_node->data, system_time, EXEC_STATE);
		}

		// if no process executing
		// then top of ready->running
		if ( 	ready_llist->head != NULL &&
				exec_llist->head == NULL ) {
			node_t* new_exec_node = get_and_remove_node(&ready_llist);
			append_node(&exec_llist, new_exec_node, sched);
			new_exec_node->data->state= EXEC_STATE;
			curr_exec_time = 0;
			string_pcb(o_file, new_exec_node->data, system_time, READY_STATE);
		}

		// Update time counters
		system_time++;
		curr_exec_time++;
		
		
		// When all processes have run (when all lists are empty), end loop
		if ( 	wait_llist->head == NULL && 
				exec_llist->head == NULL &&
				ready_llist->head == NULL &&
				pcb_llist->head == NULL ) 
		{
			system_run = 0;
		}
		
		
	}
	
	// Calculate metrics at the end

	double thru = (double) process_counter/(system_time-1);

	int total_exec = 0;
	int total_arrive = 0;
	node_t* curr_at_node;
	curr_at_node = terminated_llist->head;
	while ( curr_at_node->next != NULL) {
		total_exec += curr_at_node->data->term_time;
		total_arrive += curr_at_node->data->arrival_time;
		curr_at_node = curr_at_node->next;
	}
	double avg_turn = (double) ( (total_exec - total_arrive) / process_counter );

	int total_wait = 0; 
	node_t* curr_aw_node;
	curr_aw_node = terminated_llist->head;
	while ( curr_aw_node->next != NULL) {
		total_wait += curr_aw_node->data->wait_time;
		curr_aw_node = curr_aw_node->next;
	}
	double avg_wait = (double) total_wait / process_counter;


	// Print metrics at the end
	FILE* out_file = fopen(o_file, "a");
	fprintf(out_file, "\nThroughput,%lf,\nAverage Turnaround Time,%lf,\nAverage Wait Time,%lf,", thru, avg_turn, avg_wait);
	fclose(out_file);
		
}



