#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT 4444

int main(){

	FILE *users=fopen("users.txt","a+");
	FILE *messages=fopen("messages.txt","a+");

    char c;

    int i,j;
    i=0;
    j=0;
    char* userName=malloc(256*sizeof(char));
    char* targetUserID=malloc(256*sizeof(char));
    char** userList=malloc(sizeof(char*));
    char *buffer = malloc(256*sizeof(char));
    int userCount=0;
    int flag=0;
    fscanf(users,"%c",&c);

    while(!feof(users)){

        while(c != '\n'){
        userName[j]=c;
        j++;
        fscanf(users,"%c",&c);
        }
        fscanf(users,"%c",&c);
        userList=realloc(userList,(userCount+1)*sizeof(char*));
        userList[i]=malloc(strlen(buffer)*sizeof(char));
        //printf("Kullanici %d : %s\n",i,buffer);
        strcpy(userList[i],userName);
        userCount++;
        i++;
        j=0;
        bzero(userName,256);
    }

	int sockfd, ret;
    struct sockaddr_in serverAddr;

	int newSocket;
	struct sockaddr_in newAddr;

	socklen_t addr_size;

	pid_t childpid;
	pid_t childpid2;

    sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Server Socket is created.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(PORT);
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	ret = bind(sockfd, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("[-]Error in binding.\n");
		exit(1);
	}
	printf("[+]Bind to port %d\n", 4444);

	if(listen(sockfd, 10) == 0){
		printf("[+]Listening....\n");
	}else{
		printf("[-]Error in binding.\n");
	}

	    int k=0;
	    int offset;
	    char* source=malloc(64*sizeof(char));
	    char* target=malloc(64*sizeof(char));
	    char* message=malloc(256*sizeof(char));


	while(1){

		newSocket = accept(sockfd, (struct sockaddr*)&newAddr, &addr_size);
		if(newSocket < 0){
			exit(1);
		}
		printf("Connection accepted from %s:%d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port));

		//if((childpid = fork()) == 0){
			close(sockfd);
            int found=0;
			recv(newSocket, userName, 1024, 0);//get username
			printf("User %s has connected...\n",userName);
			for(i=0;i<userCount;i++){
                if(strcmp(userName,userList[i])==0){
                    found=1;
                }
			}
			if(found==0){
                userList=realloc(userList,(userCount+1)*sizeof(char*));
                userList[userCount]=malloc(strlen(userName)*sizeof(char));
                strcpy(userList[userCount],userName);
                fprintf(users,"%s|",userName);
                fflush(users);
                userCount++;
			}
			send(newSocket,"ok",2,0);//ok
			while(1){
				recv(newSocket, buffer, 1, 0);//choice
				send(newSocket,"ok",2,0);//ok
				int choice = atoi(buffer);
				printf("i read :%d\n",choice);
				switch(choice){
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    bzero(buffer, 256);
                    recv(newSocket, buffer, 256, 0);//message
                    //buffer[sizeof(buffer)]='\0';
                    send(newSocket,"ok",2,0);//ok
                    recv(newSocket, targetUserID, 1024, 0);//target ID
                    send(newSocket,"ok",2,0);//ok
                    fflush(stdout);
                    printf("The message:\n%s\n\n will be sent to :%s\n",buffer,targetUserID);
                    fprintf(messages,"%s->%s|%s\n",userName,targetUserID,buffer);
                    fflush(stdout);
                    fflush(messages);
                    break;


                case 5:
                    rewind(messages);
                    flag=0;
                    k=0;
                    offset=0;
                    while(fgets(buffer,256,messages)){
                            while(buffer[k]!= '-'){
                                source[k]=buffer[k];
                                k++;
                            }
                            source[k]='\0';
                            //printf("Source: %s\n",source);
                            k+=2;
                            offset=k;
                            while(buffer[k]!= '|'){
                                target[k-offset]=buffer[k];
                                k++;
                            }
                            //printf("Target: %s\n",target);
                            k++;
                            offset=k;
                            while(buffer[k]!='\0'){
                                message[k-offset]=buffer[k];
                                k++;
                            }
                            //printf("Message: %s\n",message);
                            if(strcmp(target,userName)==0){
                                //printf("From %s: %s",source,message);
                                //printf("Sending source...");
                                send(newSocket, source, strlen(source), 0);//source
                                fflush(stdout);

                                recv(newSocket, buffer, 2, 0);//ok
                                //printf("Sending message...");
                                send(newSocket, message, strlen(message),0);//message
                                recv(newSocket, buffer, 2, 0);//ok
                                fflush(stdout);
                            }

                            k=0;
                            bzero(source, 64);
                            bzero(target, 64);
                            bzero(message, 256);


                    }

                    send(newSocket, "ok",2,0);

                    break;
                }
				if(strcmp(buffer, ":exit") == 0){
					printf("Disconnected from %s:%d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port));
					break;
				}else{
					//printf("Client: %s\n", buffer);
					//send(newSocket, buffer, strlen(buffer), 0);
					bzero(buffer, sizeof(buffer));
				}
			}

		//}
        }

	close(newSocket);


	return 0;
}
