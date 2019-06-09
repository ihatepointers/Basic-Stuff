/*
 * mm_alloc.h
 *
 * A clone of the interface documented in "man 3 malloc".
 */

#ifndef MM_ALLOC_H_
#define MM_ALLOC_H_
typedef struct s_block *t_block;

struct s_block{     
    size_t size;    
    t_block next;   
    t_block prev;   
    int free;       
    char data[1];   
};

int nearest4(int x);
t_block get_block(void *p);
void copy_block(t_block dst, t_block src);
void split_block(t_block last,size_t size);
t_block find_block(t_block last, size_t size);
t_block extend_heap(t_block last, size_t s);
void *mm_malloc(size_t size);
void *mm_realloc(void *ptr, size_t size);
void mm_free(void *ptr);

#endif // MM_ALLOC_H_
