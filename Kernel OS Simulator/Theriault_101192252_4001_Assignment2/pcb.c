/* SYSC4001A Assignment 2
 * Cade Theriault
 * 101192252
 */

#include "pcb.h"


/* Loads the input file into a PCB linked list.*/
int load_csv_as_pcb (char* textfile, linkedlist_t** pcb_llist, int is_priority) {
	const char* delim = ",";
	char text[300];

	FILE *file = fopen(textfile, "r");

	/* Read first line and skip, 
	   since it is the table column titles 
	   Titles are not needed. */
	fgets(text, 300, file); 

	while (fgets(text, 300, file)) {
		// Parse the text file and split the string into the information
		pcb_t *pcb = malloc(sizeof(pcb_t));
		pcb->pid = atoi(strtok(text, delim));
		pcb->arrival_time = atoi(strtok(NULL, delim));
		pcb->total_cpu_time = atoi(strtok(NULL, delim));
		pcb->io_frequency = atoi(strtok(NULL, delim));
		pcb->io_duration = atoi(strtok(NULL, delim));
		if (is_priority == 1) {
			pcb->priority = atoi(strtok(NULL, delim));
		} else {
			pcb->priority = -1;
		}
		pcb->state = NEW_STATE;
		pcb->exec_time = 0;
		pcb->wait_time = 0;
		pcb->term_time = 0;
		pcb->total_wait = 0;
		pcb->enter_ready = 0;
		pcb->exit_ready = 0;
		
		// Add the PCB as a node to the list.
		node_t *node = new_node(pcb);
		append_node(pcb_llist, node, is_priority);
	}

	fclose(file); // Close the file
	return 0;
}

/*Updates the output file with the most recent transition of a process. */
void string_pcb(char* o_file, pcb_t* pcb, int sys_time, char* old_state, int is_priority) {
	FILE* output_file = fopen(o_file, "a");

	if (is_priority == 1) {
		fprintf(output_file, "%d,%d,%d,%s,%s\n", sys_time, pcb->pid, pcb->priority, old_state, pcb->state);
	} else {
		fprintf(output_file, "%d,%d,%s,%s\n", sys_time, pcb->pid, old_state, pcb->state);
	}
	fclose(output_file);
}

