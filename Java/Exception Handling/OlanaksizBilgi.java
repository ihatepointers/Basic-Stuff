
import java.io.IOException;
/* E�er RuntimeException'dan t�retsek 
 * t�m kodlar�m�z nas�l olacakt� inceleyiniz.
 */
@SuppressWarnings("serial")
public class OlanaksizBilgi extends IOException {
	public OlanaksizBilgi( ) { 
		super( ); 
	}

	public OlanaksizBilgi( String hataMesaji ) { 
		super(hataMesaji); 
	}
}
