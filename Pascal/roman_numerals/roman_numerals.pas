program spartacus;
uses crt;

var number,th,fh,h,ff,t,f:integer;

begin
  writeln('Enter a number between 1 and 4000');
  readln(number);

    th:=(number DIV 1000);
    fh:=((number MOD 1000) DIV 500);
    h:=((number MOD 500) DIV 100);
    ff:=((number MOD 100) DIV 50);
    t:=((number MOD 50) DIV 10);
    f:=((number MOD 10) DIV 5);

    if th<>0 then
     repeat
      write('M');
      th:=th-1;
     until th=0;
    if fh<>0 then if (number mod 1000)>899 then
    begin
    write('CM');
    h:=0;
    end
    else
     repeat
      write('D');
      fh:=fh-1;
     until fh=0;
    if h<>0 then
     if h>3 then write ('CD')
     else
     repeat
      write('C');
      h:=h-1;
     until h=0;
    if ff<>0 then
     if (number MOD 100)>89 then
     begin
     write('XC');
     t:=0;
     end
     else
     repeat
      write('L');
      ff:=ff-1
     until ff=0;
    if t<>0 then
     if t>3 then write ('XL') else
     repeat
      write('X');
      t:=t-1;
     until t=0;
    if f<>0 then
     if (number MOD 10)=9 then write('IX')
     else write('V');
     if number mod 5=4 then
     if (number MOD 10)<>9 then write('IV');
     if number mod 5=3 then write('III');
     if number mod 5=2 then write('II');
     if number mod 5=1 then write('I');
     writeln();
  readln;
end.
