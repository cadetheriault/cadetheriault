 /* SYSC4001A Assignment 2
 * Cade Theriault
 * 101192252
 */

#include "node.h"

/* Creates a new node.*/
node_t *new_node(pcb_t *val) {
	node_t* node = malloc(sizeof(node_t));
	node->data = val;
	node->next = NULL;
	return node;
}
/* Appends a node to a linked list.*/ 
void append_node(linkedlist_t** pcb_list, node_t* new_node, int is_priority){

	
	// Check if list is empty (or if head pointer is NULL)
	if ((*pcb_list)->head == NULL){
		(*pcb_list)->head = new_node;
		return;
	}

	// Check Priority scheduling
	if (is_priority == 1) {
		// Sort as placed in linkedlist
		node_t* curr_node;
		curr_node = (*pcb_list)->head;
		
		if(new_node->data->priority < curr_node->data->priority){
				node_t *temp = curr_node;
				(*pcb_list)->head = new_node;
				new_node->next = temp;
				return;
		}


		while (curr_node->next != NULL){
			if(new_node->data->priority < curr_node->next->data->priority){
				node_t *temp = curr_node->next;
				curr_node->next = new_node;
				new_node->next = temp;
				return;
			}
			
			curr_node = curr_node->next;
			// Itearte if not at the end of linkedlist or if the priority is lower than current
		}
		
		curr_node->next = new_node;

	// FCFS or RR scheduling
	} else { 
		// Place node at the end of linkedlist
		node_t* curr_node;
		curr_node = (*pcb_list)->head;
		while ( curr_node->next != NULL) {
			// Itearte if not at the end of linkedlist
			curr_node = curr_node->next;
		}
		curr_node->next = new_node;
	}
	
}

/* Creates a new linked list. */
linkedlist_t* new_linkedlist(){

	linkedlist_t* llist = malloc(sizeof(linkedlist_t));
	llist->head = NULL;
	return llist;
}

/* Gets and removes node from the top of a linked list. */
node_t *get_and_remove_node(linkedlist_t** list){
	
	if ( (*list)->head == NULL) {
		return NULL;
	}
	
	node_t* removed_node;
	removed_node = (*list)->head;
	(*list)->head = (*list)->head->next;
	removed_node->next = NULL;
	
	return removed_node;

}
