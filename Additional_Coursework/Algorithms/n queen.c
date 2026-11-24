#include <stdio.h>
#include <stdlib.h>

#define SIZE 8
int board[SIZE];
int isSafe(int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i] == col || abs(board[i] - col) == abs(i - row)) {
            return 0;
        }
    }
    return 1;
}

int solve(int row) {
    if (row == SIZE) {
        return 1;
    }

    for (int col = 0; col < SIZE; col++) {
        if (isSafe(row, col)) {
            board[row] = col;
            if (solve(row + 1)) {
                return 1;
            }
        }
    }
    return 0;
}

void printBoard() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i] == j) {
                printf("Q ");
            } else {
                printf(". ");
            }
        }
        printf("\n");
    }
}

int main() {
    if (solve(0)) {
        printf("Solution to 8 Queens problem:\n");
        printBoard();
    } else {
        printf("No solution found.\n");
    }
    return 0;
}