/* SYSC4001A Assignment 1
 * Cade Theriault
 * 101192252
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pcb.h"
#include "node.h"

int main(int argc, char** argv) {

	int system_run = 1; // boolean variable for system loop

	// Time counters
	int system_time = 0; 
	int curr_exec_time = 0; 

	// Grab input and output file names
	char* i_file = argv[1];
	char* o_file = argv[2];
	
	// Clear the output file before adding text to it
	FILE* output_file = fopen(o_file, "w");
	fclose(output_file);
	
	output_file = fopen(o_file, "a");
	fprintf(output_file, "Time of Transition,PID,Old State,New State\n");
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

	
	// Open and read csv file, and write each process into the PCB list
	int load_result = load_csv_as_pcb(i_file, &pcb_llist);
	
	// Clock loop
	while (system_run) {
		
		// if system_time >= arrival_time
		// then new->ready
		while ( pcb_llist->head != NULL && 
				system_time >= pcb_llist->head->data->arrival_time ) {
			node_t* new_ready_node = get_and_remove_node(&pcb_llist);
			append_node(&ready_llist, new_ready_node);
			new_ready_node->data->state = READY_STATE;
			string_pcb(o_file, new_ready_node->data, system_time, NEW_STATE);
		}

		// if  wait_time >= io_duration
		// then waiting->ready
		while ( wait_llist->head != NULL && 
				( system_time - wait_llist->head->data->wait_time ) >= wait_llist->head->data->io_duration 
			) {
			node_t* new_ready_node = get_and_remove_node(&wait_llist);
			append_node(&ready_llist, new_ready_node);
			new_ready_node->data->state = READY_STATE;
			string_pcb(o_file, new_ready_node->data, system_time, WAITING_STATE);

		}

		// if execution_time >= total_cpu_time
		// then running->terminated
		if ( 	exec_llist->head != NULL && 
				(curr_exec_time + exec_llist->head->data->exec_time) >= exec_llist->head->data->total_cpu_time 
			) {
			node_t* new_term_node = get_and_remove_node(&exec_llist);
			new_term_node->data->state = TERMINATED_STATE;
			string_pcb(o_file, new_term_node->data, system_time, EXEC_STATE);

		}

		// if execution_time >=IO_frequency
		// then running->waiting
		if ( 	exec_llist->head != NULL && 
				curr_exec_time >= exec_llist->head->data->io_frequency 
			) {
			node_t* new_wait_node = get_and_remove_node(&exec_llist);
			append_node(&wait_llist, new_wait_node);
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
			append_node(&exec_llist, new_exec_node);
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
	
		
}



