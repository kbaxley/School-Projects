.global _start

.data

fib: .word 1
prev: .word 0

.text

main:
	push {ip, lr}
   
	//Registers for local variables
	mov r0, #0
	mov r1, #1 // n = 1
	mov r2, #0 // sum = 0
	mov r3, #10 // 10
	
	//Registers for global variables
	ldr r4, =fib
	ldr r4, [r4]
	
	ldr r5, =prev
	ldr r5, [r5]

while:
	//While condition
	cmp r1, r3
	bgt done
	
	//Loop body
	add r2, r4, r5 //sum = fib + prev
	mov r5, r4 //prev = fib
    mov r4, r5 //fib = sum
    add r1, #1 //n++
	b while
	
done:
	mov r0, r4
	
	pop {ip, pc}
