
Copy
#include <stdio.h>
#include <malloc.h>
#define MAX(a,b) ((a)>(b)? (a):(b))

// 문자열 길이 계산
int _strlen(char* str) {
	int size = 0;
	while (str[size++]);
	return size - 1;
}

// 문자열 앞에 '0' 추가
void strAddZero(char* str) {
	if (_strlen(str) == 0)
	{
		str[0] = '\0';
		return;
	}
	int cur = 0;
	char temp = str[cur];
	char temp2;
	while (str[cur]) {
		temp2 = str[cur + 1];
		str[cur + 1] = temp;
		temp = temp2;
		cur++;
	}
	str[cur + 1] = '\0';
	str[0] = '0';
}

int main() {
	int Table[15][15] = { 0, }; // LCS 길이를 저장할 테이블
	char str1[10] = { 'A','B','C','B','D','A','B' }; // 첫 번째 문자열
	char str2[10] = { 'B','D','C','A','B','A' };     // 두 번째 문자열
	int str1_len;
	int str2_len;

	// 문자열 앞에 '0' 추가
	strAddZero(str1);
	strAddZero(str2);
	str1_len = _strlen(str1);
	str2_len = _strlen(str2);

	// LCS 테이블 채우기
	for (int i = 1; i < str1_len; i++) {
		for (int j = 1; j < str2_len; j++) {
			if (str1[i] == str2[j]) {
				Table[i][j] = Table[i - 1][j - 1] + 1; // 문자가 같으면 대각선 값 + 1
			}
			else {
				Table[i][j] = MAX(Table[i - 1][j], Table[i][j - 1]); // 다르면 위나 왼쪽 값 중 큰 것
			}
		}
	}

	char LCS_Str[10] = { '10', }; // LCS 문자열 저장
	int LCS_len = Table[str1_len - 1][str2_len - 1] - 1; // LCS 길이
	int i = str1_len - 1;

	// LCS 문자열 역추적
	for (int j = str2_len - 1; j > 0; )
	{
		if (Table[i][j] == Table[i - 1][j]) {
			i--;
		}
		else if (Table[i][j] == Table[i][j - 1]) {
			j--;
		}
		else if (Table[i - 1][j] == Table[i][j - 1]) {
			LCS_Str[LCS_len--] = str2[j--];
			i--;
		}
		else if (Table[i - 1][j] == Table[i][j - 1]) {
			LCS_Str[LCS_len--] = str2[j--];
		}
	}

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 7; j++) {
			printf("%d", Table[i][j]);
		}
		printf("\n");

	}
	printf("LCS Size : %d, LCS String : %s\n", Table[str1_len - 1][str2_len - 1], LCS_Str);
}
