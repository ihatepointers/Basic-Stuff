
import java.io.IOException;
/* Eðer RuntimeException'dan türetsek 
 * tüm kodlarýmýz nasýl olacaktý inceleyiniz.
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
