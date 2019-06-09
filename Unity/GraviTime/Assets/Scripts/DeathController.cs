using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathController : MonoBehaviour {

    

    public void deathTrigger()
    {
        Debug.Log("Died");

        this.GetComponent<DeathScreen>().startDeathScreen();
        
    }

    
}
