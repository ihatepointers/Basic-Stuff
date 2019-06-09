use strict;
use warnings;
use Scalar::Util qw(looks_like_number);
use 5.010;
open(file, "array.txt");


my @resultArray;
while(my $line = <file>){
    chomp $line;
    my @linearray = split(" ", $line);
    push(@resultArray, @linearray);
}

my $i=0;
my $j=0;
my $k=0;

my $arrSize = @resultArray;
print "Size of the array: $arrSize\n";
print "Array from txt file: ";

for($i=0; $i < $arrSize; $i +=1){
    print"$resultArray[$i] ";
}
print "\n\n";
print "Specify the window size: ";
my $windowSize = <STDIN>;

chomp $windowSize;


while((!looks_like_number($windowSize)) or ($windowSize < 0)){
    print "Window size has to be a positive integer: ";
    $windowSize = <STDIN>;
    chomp $windowSize;
}

my $startingIndex = 0;
my $endingIndex = 0;if (($windowSize % 2) == 0){
    $endingIndex = $arrSize - 1 - ($windowSize/2);
}else{
    $endingIndex = $arrSize - int($windowSize/2);}

my $median=0;
my @medianArray = ();
print "\n";
for($i=0; $i < $arrSize; $i +=1){
    print"$resultArray[$i] |";
}
print " - Original Array\n";

for($i=0; $i < $startingIndex; $i +=1){
    print" ";
}



my @windowArray = ();
my @sortedWindowArray = ();
if(($windowSize % 2) == 0){
    $i=0;
    
    while( ($i + $windowSize) < ($arrSize+1) ){
        @windowArray = ();
        for($j=0; $j < $windowSize; $j+=1){
            push(@windowArray,$resultArray[$i+$j]);
        }
        @sortedWindowArray = sort { $a <=> $b } @windowArray;
        
        for($j=0; $j < $windowSize; $j +=1){
            #print "$sortedWindowArray[$j] ";
        }
        #print"\n";
        $median = ($sortedWindowArray[$windowSize/2-1] + $sortedWindowArray[$windowSize/2]) / 2;
        #print "$median |";
        push(@medianArray, $median);
        $i+=1;
    }
}else{
    while( ($i + $windowSize) < ($arrSize+1) ){
        @windowArray = ();
        for($j=0; $j < $windowSize; $j+=1){
            push(@windowArray,$resultArray[$i+$j]);
        }
        @sortedWindowArray = sort { $a <=> $b } @windowArray;
        
        for($j=0; $j < $windowSize; $j +=1){
            #print "$sortedWindowArray[$j] ";
        }
        #print"\n";
        $median = $sortedWindowArray[int($windowSize/2)];
        push(@medianArray, $median);
        #print "$median |";
        $i+=1;
    }
}


my $medianArraySize = @medianArray;
print "\n\n";
for($i=0; $i < $medianArraySize; $i +=1){
    print"$medianArray[$i] |";
}


print " - Median Array\n\n";

print "===========================================Line Plots===========================================\n";
my @linePlotArrayX = sort { $a <=> $b } @resultArray;
my @linePlotArrayY = sort { $a <=> $b } @medianArray;

my @linePlotArrayCleanedX = ();
my @linePlotArrayCleanedY = ();

my @dotCountX = ();
my @dotCountY = ();

my $maxDotX = 1;
my $maxDotY = 1;

my $l = 0;
print "Original Array Line Plot(X):\n";
#print"$resultArray[0]. *";
push(@linePlotArrayCleanedX, $linePlotArrayX[0]);
push(@dotCountX, 1);
my $dotCountIndex = 0;
for($i=1; $i < $arrSize; $i +=1){
    if( $linePlotArrayX[$i] == $linePlotArrayX[$i-1]){
        $dotCountX[$dotCountIndex] += 1;
        if($dotCountX[$dotCountIndex] > $maxDotX){
            $maxDotX = $dotCountX[$dotCountIndex];        }    }else{
        push(@linePlotArrayCleanedX, $linePlotArrayX[$i]);
        push(@dotCountX, 1);
        $dotCountIndex += 1;
    }
}

my $linePlotArrayCleanedXSize = @linePlotArrayCleanedX;


$dotCountIndex = 0;
push(@linePlotArrayCleanedY, $linePlotArrayY[0]);
push(@dotCountY, 1);
for($i=1; $i < $medianArraySize; $i +=1){
    if( $linePlotArrayY[$i] == $linePlotArrayY[$i-1]){
        $dotCountY[$dotCountIndex] += 1;
        if($dotCountY[$dotCountIndex] > $maxDotY){
            $maxDotY = $dotCountY[$dotCountIndex];
        }
    }else{
        push(@linePlotArrayCleanedY, $linePlotArrayY[$i]);
        push(@dotCountY, 1);
        $dotCountIndex += 1;
    }
}
my $linePlotArrayCleanedYSize = @linePlotArrayCleanedY;


print"\n\n";

for($i=0; $i < $linePlotArrayCleanedXSize; $i +=1){
    print"$linePlotArrayCleanedX[$i] ";}
print"\n";

for($i=0; $i < $linePlotArrayCleanedXSize; $i +=1){
    print"--";
    $l = $linePlotArrayCleanedX[$i];
    while( int($l/10) > 0){
        print"-";
        $l = int($l/10);
    }
    if(($linePlotArrayCleanedX[$i]) != int($linePlotArrayCleanedX[$i])){
        print"--";
    }
}
print"\n";

for($i=0; $i < $maxDotX; $i +=1){
    for($j=0; $j < $linePlotArrayCleanedXSize; $j +=1){
        $k = $i+1;

        if($dotCountX[$j] >= ($i+1)){
            print"| ";        }else{
            print"  ";        }
        $l = $linePlotArrayCleanedX[$j];
        while( int($l/10) > 0){
            print" ";
            $l = int($l/10);
        }
        if(($linePlotArrayCleanedX[$j]) != int($linePlotArrayCleanedX[$j])){
            print"  ";
        }   
    }
    print"\n";
}

print "\n\nMedian Array Line Plot(Y):";
print"\n\n";for($i=0; $i < $linePlotArrayCleanedYSize; $i +=1){
    print"$linePlotArrayCleanedY[$i] ";
}
print"\n";

for($i=0; $i < $linePlotArrayCleanedYSize; $i +=1){
    print"--";
    $l = $linePlotArrayCleanedY[$i];
    while( int($l/10) > 0){
        print"-";
        $l = int($l/10);
    }
    if(($linePlotArrayCleanedY[$i]) != int($linePlotArrayCleanedY[$i])){
        print"--";
    }

}
print"\n";

for($i=0; $i < $maxDotY; $i +=1){
    for($j=0; $j < $linePlotArrayCleanedYSize; $j +=1){
        $k = $i+1;

        if($dotCountY[$j] >= ($i+1)){
            print"| ";
        }else{
            print"  ";
        }
        $l = $linePlotArrayCleanedY[$j];
        while( int($l/10) > 0){
            print" ";
            $l = int($l/10);
        }
        if(($linePlotArrayCleanedY[$j]) != int($linePlotArrayCleanedY[$j])){
            print"  ";        }
    }
    print"\n";
}
print"\n";

