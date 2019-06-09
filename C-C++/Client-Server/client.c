#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>   //_getch*/
#include <termios.h>  //_getch*/
#define PORT 4444

char getch(){

    char buf=0;
    struct termios old={0};
    fflush(stdout);
    if(tcgetattr(0, &old)<0)
        perror("tcsetattr()");
    old.c_lflag&=~ICANON;
    old.c_lflag&=~ECHO;
    old.c_cc[VMIN]=1;
    old.c_cc[VTIME]=0;
    if(tcsetattr(0, TCSANOW, &old)<0)
        perror("tcsetattr ICANON");
    if(read(0,&buf,1)<0)
        perror("read()");
    old.c_lflag|=ICANON;
    old.c_lflag|=ECHO;
    if(tcsetattr(0, TCSADRAIN, &old)<0)
        perror ("tcsetattr ~ICANON");
    //printf("%c\n",buf);
    return buf;
 }

int main(int argc, char *argv[]){
	int choice=-1;
	int flag=0;
	int clientSocket, ret;
	struct sockaddr_in serverAddr;
	char buffer[1024];

	clientSocket = socket(AF_INET, SOCK_STREAM, 0);
	if(clientSocket < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Client Socket is created.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(PORT);
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	ret = connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Connected to Server.\n");
    //argv[1]="serdar";
	while(1){
		while(choice){
		system("clear");
		printf("Welcome %s !",argv[1]);
		printf("\n\n");
		//printf("1. List contacts\n");
		//printf("2. Add User\n");
		//printf("3. Delete User\n");
		printf("4. Send Message\n");
		printf("5. Check Messages\n");
		printf("\n\n");
		scanf("%d",&choice);
		switch(choice){
		    case 1:
		        break;

		    case 2:
		        break;

		    case 3:
		        break;

		    case 4:
		        send(clientSocket, argv[1], strlen(argv[1]), 0);//username
			recv(clientSocket, buffer, 2, 0);//ok
		        send(clientSocket, "4", 1, 0);//choice

		printf("Enter the message: ");
                scanf("%s", &buffer[0]);//gets
                send(clientSocket, buffer, strlen(buffer), 0);//message
		recv(clientSocket, buffer, 2, 0);//ok

		printf("Enter the target user ID: ");
		scanf("%s", &buffer[0]);//gets
		send(clientSocket, buffer, strlen(buffer), 0);//target ID
		recv(clientSocket, buffer, 2, 0);//ok

                if(strcmp(buffer, ":exit") == 0){
                    close(clientSocket);
                    printf("[-]Disconnected from server.\n");
                    exit(1);
                }

                if(recv(clientSocket, buffer, 1024, 0) < 0){
                    printf("[-]Error in receiving data.\n");
                }else{
                    printf("Server: \t%s\n", buffer);
                }
                printf("Press enter to continue...");
                getchar();

		        break;

		    case 5:
            flag=0;
			send(clientSocket, argv[1], strlen(argv[1]), 0);//username
			recv(clientSocket, buffer, 2, 0);//ok
            send(clientSocket, "5", 1, 0);//choice
			recv(clientSocket, buffer, 2, 0);//ok

			bzero(buffer, 256);
            recv(clientSocket, buffer, 256, 0);//get from
            send(clientSocket,"ok",2,0);//ok
            if((buffer[0]=='o')&&(buffer[1]=='k')){
                flag=1;
            }
			while(flag==0){//if not ok
				printf("From %s: ",buffer);
				fflush(stdout);
				bzero(buffer, 256);

				recv(clientSocket, buffer, 256, 0);//get message
				send(clientSocket,"ok",2,0);//ok
				printf("%s\n",buffer);
				fflush(stdout);
				bzero(buffer, 256);

				recv(clientSocket, buffer, 256, 0);
				send(clientSocket,"ok",2,0);//ok
				if((buffer[0]=='o')&&(buffer[1]=='k')){
                    flag=1;
                }
			}
                printf("\nPress enter to continue...");
                getch();
		        break;

		    case 0:
		        close(clientSocket);
		        exit(0);

        }
		}


	}

	return 0;
}
