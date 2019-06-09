use strict;
use warnings;
use Scalar::Util qw(looks_like_number);

my $sequence1;
my $sequence2;

open(file1, "sequence1.txt");
while(<file1>){
    $sequence1 = $_;
}

open(file2, "sequence2.txt");
while(<file2>){
    $sequence2 = $_;
}

print "Window size of the dotplot: ";
my $windowSize = <STDIN>;

chomp $windowSize;

while(!looks_like_number($windowSize)){
    print "\nPlease enter a number: ";
    $windowSize = <STDIN>;
    chomp $windowSize;
}

print "Threshold of the dotplot: ";
my $threshold = <STDIN>;

chomp $threshold;

while(!looks_like_number($threshold)){
    print "\nPlease enter a number: ";
    $threshold = <STDIN>;
    chomp $threshold;
}



my @dotplot;
my @sequence1Array = split(//,"$sequence1");
my @sequence2Array = split(//,"$sequence2");
my $i=0;
my $j=0;
my $k=0;
my $l=0;
my $rowNumber = length($sequence1);
my $columnNumber = length($sequence2);
my $matchCount=0;


for($i=0; $i < $rowNumber; $i +=1){
    for($j=0; $j < $columnNumber; $j +=1){
    	if( $sequence1Array[$j] eq $sequence2Array[$i]){
	        for($k=0; $k < $windowSize; $k +=1){
	            if(((($j+$k)<$rowNumber) && (($i+$k)<$columnNumber)) && ( $sequence1Array[$j+$k] eq $sequence2Array[$i+$k])){
	                $matchCount += 1;
	            }     
	        }
	        if( $matchCount >= $threshold){
	            $dotplot[$i][$j] = '*';
	        }else{
	            $dotplot[$i][$j] = ' ';
	        }
	        $matchCount=0;
    	}else{
    		$dotplot[$i][$j] = ' ';
    	}
    }
}



	

print "Sequence 1: $sequence1\n";
print "Sequence 2: $sequence2\n";
print "Dot Plot: \n\n";

print "  |";
for($i=0; $i < $rowNumber; $i +=1){
    print"$sequence1Array[$i]|";
}
print "\n  ";
for($i=0; $i < $rowNumber*2+1; $i +=1){
    print"-";
}
print "\n";
for($i=0; $i < $rowNumber; $i +=1){
    print " $sequence2Array[$i]";
    for($j=0; $j < $rowNumber; $j +=1){
        print "|$dotplot[$i][$j]";
    }
    print "|";
    print "\n ";
    for($k=0; $k < $rowNumber+1; $k +=1){
        print "-|";
    }
    print"\n";
}
print"\n";