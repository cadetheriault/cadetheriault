/* SYSC4001A Assignment 1
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
void append_node(linkedlist_t** pcb_list, node_t* new_node ){

	// Check if list is empty (or if head pointer is NULL)
	if ((*pcb_list)->head == NULL){
		(*pcb_list)->head = new_node;
		return;
	}

	node_t* curr_node;
	curr_node = (*pcb_list)->head;
	while ( curr_node->next != NULL) {
		curr_node = curr_node->next;
	}
	curr_node->next = new_node;

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
