/* SYSC4001A Assignment 2
 * Cade Theriault
 * 101192252
 */

#ifndef __STRUCTURES_H__
#define __STRUCTURES_H__

//States:

#define NEW_STATE (char*) "NEW"
#define READY_STATE (char*) "READY"
#define EXEC_STATE (char*) "EXECUTING"
#define WAITING_STATE (char*) "WAITING"
#define TERMINATED_STATE (char*) "TERMINATED"

typedef struct PCB {
	int pid;
	int arrival_time;
	int total_cpu_time;
	int io_frequency;
	int io_duration;
	int priority;
	int exec_time;
	int wait_time;
	int term_time;
	char* state;
}pcb_t;

typedef struct Node {
	pcb_t *data;
	struct Node *next;
}node_t;


typedef struct LinkedList {
	node_t *head;
}linkedlist_t;

#endif
