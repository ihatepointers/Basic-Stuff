program raceway;
uses crt,dos;
var userchoice,row,column,ElapsedTime,joker,i,j,x,tc,k,l,t,t2,tend,wrong,round,choice:integer;
  s,tmpScore:Extended;
  tmpName:string;
  hr,min,sec,hsec,hr2,min2,sec2,hsec2: word;
  ElapsedTimeArray:Array[1..10] of integer;
  A:Array[1..100,1..100] of integer;
  Name:Array[1..10] of string[12];
  Score:Array[1..10] of Extended;
begin
 HighVideo;
 tend:=0;
 randomize;

 s:=0;
 repeat
 joker:=1;
 for i:=1 to row do
  for j:=1 to column do
   A[i,j]:=0;
 repeat
 clrscr;
 writeln;
 writeln;
 Writeln('************************** Welcome to the memory game **************************');
 writeln;
 writeln;
 writeln('                              [1].Start the game');
 writeln;
 writeln('                               [2].High Scores');
 writeln;
 writeln('                               [3].How to play');
 writeln;
 writeln('                                  [4].Exit');
 writeln;
 writeln;
 writeln;
 Readln(UserChoice);
 Case (UserChoice) of
 2:
 begin
 clrscr;
 for i:=1 to tend do
  for j:=1 to tend do
   if Score[j+1]>Score[j] then
   begin
    tmpName:=Name[j];
    Name[j]:=Name[j+1];
    Name[j+1]:=tmpName;

    tmpScore:=Score[j];
    Score[j]:=Score[j+1];
    Score[j+1]:=tmpScore;
   end;
 writeln;
 writeln;
 writeln('********************************* High Scores **********************************');
 writeln;
 for i:=1 to tend do
  begin
 write('                          ',i,'.',Name[i],'..........');
 GoToXY(40,WhereY);
 write('....',Score[i]:0:0,'  ');
 GoToXY(49,WhereY);
 ElapsedTime:=ElapsedTimeArray[i];
 if ElapsedTime DIV 3600<10 then write('0');
 write(ElapsedTime DIV 3600,':');
 if ElapsedTime MOD 3600 DIV 60<10 then write('0');
 write(ElapsedTime MOD 3600 DIV 60,':');
 if ElapsedTime MOD 60<10 then write('0');
 writeln(ElapsedTime MOD 60);
  end;
 writeln;
 writeln('Press any key to go back to main menu');
 repeat
 until KeyPressed;
 end;
 3 :
 begin
 clrscr;
 writeln('******************************* How to Play ***********************************');
 writeln('This game is designed to test your memory.In this game,you will');
 writeln('have to memorize a raceway in a predetermined time.');
 writeln('In this raceway,there are red zeroes and green ones.Red zeroes');
 writeln('represent the wrong blocks which you should avoid.Green ones');
 writeln('are the blocks that you have to memorize.When the countdown reaches');
 writeln('zero,the apparent blocks will be marked as *.This means it is your');
 writeln('time to choose the correct block in each column one by one.There');
 writeln('can be more than one correct blocks in a column based on your choices');
 writeln('at the beginning of the game.If there are more than 1 correct blocks,');
 writeln('remembering just one of them is enough to proceed to the next');
 writeln('column.At the beginning of the game you will be asked several');
 writeln('questions about the game like the amount of rows and columns,');
 writeln('number of the correct blocks in each column and how much time');
 writeln('you will get to memorize the raceway.More rows,columns and correct');
 writeln('blocks make the game harder but they will get you more points if');
 writeln('you manage to remember the correct block(s).More time on the other');
 writeln('hand makes the game easier so more time means less points.');
 writeln('You have one joker.Using joker will let you to proceed');
 writeln('to the next column without giving the correct answer');
 writeln('you can use joker by choosing the row 0.Using joker will not');
 writeln('affect your game score.');
 writeln('Have fun...');
 writeln;
 write('Press any key to go back to main menu.');
 repeat
 until KeyPressed;
 clrscr;
 end;
 4 : Exit;
 end;
 until UserChoice=1;
 clrscr;
 writeln;
 writeln('How many rows do you want?');
 readln(row);
 writeln('How many columns do you want?');
 readln(column);
 writeln('How many true blocks should there be in a single column?');
 readln(tc);
 writeln('How many seconds do you need to memorize?');
 readln(t);
 t2:=t;

 for j:=1 to column do
 begin
 x:=0;
 repeat
 k:=Random(row)+1;
 if A[k,j]=0 then
 begin
 A[k,j]:=1;
 x:=x+1
 end;
 until x=tc;
 end;
  clrscr;
  writeln;
 for i:=1 to row do
  begin
  write('  ');
  for k:=1 to column*2 do
   write('-');
  writeln();
  write(i,'.');
  for j:=1 to column do
   begin
   write('|');
   if A[i,j]=1 then
   begin
   TextColor(90);
   write(A[i,j]);
   TextColor(White);
   end
   else
   begin
   TextColor(124);
   write(A[i,j]);
   TextColor(White);
   end;
   if j=column then write('|');
   end;

  writeln();
  if i=row then
  begin
  write('  ');
  for j:=1 to column*2 do write('-');
  end;

   end;
 writeln;
 writeln;
 repeat
  delline;
  t2:=t2-1;
 write('The game will begin in ',t2,' second(s)');
 delay(1000);
 delline;
 GotoXY(1,wherey);
 until t2=0;
 clrscr;
 writeln;
 GetTime (hr,min,sec,hsec);
 for i:=1 to row do
  begin

  write('  ');
  for k:=1 to column*2 do
   write('-');
  writeln();
  write(i,'.');

  for j:=1 to column do
   begin
   write('|*');
   if j=column then write('|');
   end;

  writeln();
  if i=row then
  begin
  write('  ');
  for l:=1 to column*2 do write('-');
  writeln();
  end;
  end;
 round:=0;
 wrong:=0;
 begin
 for j:=1 to column do
  if wrong=0 then
  begin
  if joker=1 then
  begin
  writeln;
  textcolor(89);
  writeln('You have 1 joker left.Write 0 to use it.');
  textcolor(white);
  end;
  round:=round+1;
  read(choice);
  if choice=0 then
  if joker=1 then
  begin
  for i:=1 to row do
   if A[i,j]=1 then choice:=i;
  joker:=joker-1
  end;
    if A[choice,j]=1 then
    begin
    clrscr;
    writeln;

  for i:=1 to row do
  begin

  write('  ');
  for k:=1 to column*2+1 do
   write('-');
  writeln();
  write(i,'.');

  for l:=1 to round do
   begin
   write('|');
   if A[i,l]=1 then
   begin
   textcolor(90);
   write(A[i,l]);
   end
   else
   begin
   textcolor(124);
   write(A[i,l]);
   end;
   textcolor(white);
   if i=column then write('|');
   end;

  for l:=round to column do
   begin
   if l=column then
   write('|')
   else write('|*');
   if i=column then write('|');
   end;

  writeln();
  if i=row then
  begin
  write('  ');
  for l:=1 to column*2+1 do write('-');
  writeln();
  end;
  end;
  end else
 begin
 wrong:=wrong+1;
 clrscr;
 writeln;
    for i:=1 to row do
  begin

  write('  ');
  for k:=1 to column*2+1 do
   write('-');
  writeln();
  write(i,'.');

  for l:=1 to round-1 do
   begin
   write('|');
   if A[i,l]=1 then
   begin
   textcolor(90);
   write(A[i,l]);
   end
   else
   begin
   textcolor(124);
   write(A[i,l]);
   end;
   textcolor(white);
   if i=column then write('|');
   end;

  for l:=round-1 to column do
   begin
   if i=choice then
   begin
   write('|');
   textcolor(124);
   write('1');
   textcolor(white);
   choice:=0;
   end
   else
   begin
   if l=column then
   write('|')
   else
   write('|*');
   end;
   if i=column then write('|');

   end;

  writeln();
  if i=row then
  begin
  write('  ');
  for l:=1 to column*2+1 do write('-');
  writeln();
  end;
  end;
 end;
end;
end;
Gettime (hr2,min2,sec2,hsec2);
Elapsedtime:=((sec2-sec)+((min2-min)*60)+(hr2-hr)*60*60);
writeln;
s:=(round)*(row+column)*tc/t;
if wrong=0 then
writeln('Congratulations!You have finished the game.Your score:',s:0:0)
else
writeln('Game over.Your score:',s:0:0);
writeln;
Writeln('You have completed the game in ',ElapsedTime,' seconds');
writeln;
tend:=tend+1;
ElapsedTimeArray[tend]:=ElapsedTime;
Score[tend]:=s;
writeln('Please enter your name');
writeln;
readln(Name[tend]);
readln(Name[tend]);
until userchoice=4 ;
readln;
readln;
end.

