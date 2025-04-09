#include "game_Field.h"
#include <vector>

int board[6][7] = {0};
bool isPlayerOneTurn = true;
int gameStatus = 2;


JNIEXPORT jobjectArray JNICALL Java_game_Field_getGameBoard(JNIEnv* env, jobject obj) {
    jclass intArrayClass = env->FindClass("[I");
    jobjectArray result = env->NewObjectArray(6, intArrayClass, nullptr);

    for (int i = 0; i < 6; ++i) {
        jintArray row = env->NewIntArray(7);
        jint temp[7];
        for (int j = 0; j < 7; ++j) {
            temp[j] = board[i][j];
        }
        env->SetIntArrayRegion(row, 0, 7, temp);
        env->SetObjectArrayElement(result, i, row);
        env->DeleteLocalRef(row);
    }
    return result;
}


JNIEXPORT jboolean JNICALL Java_game_Field_makeMove(JNIEnv* env, jobject obj, jint position) {
    if (position < 0 || position >= 7) return JNI_FALSE;

    int playerToken = isPlayerOneTurn ? 1 : 5;

    for (int i = 5; i >= 0; --i) {
        if (board[i][position] == 0) {
            board[i][position] = playerToken;
            isPlayerOneTurn = !isPlayerOneTurn;
            return JNI_TRUE;
        }
    }
    return JNI_FALSE; // Column is full
}


JNIEXPORT jint JNICALL Java_game_Field_getWinner(JNIEnv* env, jobject obj) {
    for (int i = 0; i < 6; ++i) {
        for (int j = 0; j < 7; ++j) {
            int token = board[i][j];
            if (token == 0) continue;

            // vertical
            if (i <= 2 && token == board[i + 1][j] && token == board[i + 2][j] && token == board[i + 3][j]) {
                return token;
            }
            // horizontal
            if (j <= 3 && token == board[i][j + 1] && token == board[i][j + 2] && token == board[i][j + 3]) {
                return token;
            }
            //  diagonal down-right
            if (i <= 2 && j <= 3 && token == board[i + 1][j + 1] && token == board[i + 2][j + 2] && token == board[i + 3][j + 3]) {
                return token;
            }
            // diagonal down-left
            if (i <= 2 && j >= 3 && token == board[i + 1][j - 1] && token == board[i + 2][j - 2] && token == board[i + 3][j - 3]) {
                return token;
            }
        }
    }

    // draw
    for (int j = 0; j < 7; ++j) {
        if (board[0][j] == 0) {
            return 2;
        }
    }
    return 0;
}


JNIEXPORT void JNICALL Java_game_Field_resetGame(JNIEnv* env, jobject obj) {
    for (int i = 0; i < 6; ++i) {
        for (int j = 0; j < 7; ++j) {
            board[i][j] = 0;
        }
    }
    isPlayerOneTurn = true;
    gameStatus = 2;
}


JNIEXPORT jboolean JNICALL Java_game_Field_mouseClick(JNIEnv* env, jobject obj, jint x) {
    int column = (x - 80) / 80;
    return Java_game_Field_makeMove(env, obj, column); // Only column
}