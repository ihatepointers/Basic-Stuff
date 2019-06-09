using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathScreen : MonoBehaviour {

    public GameObject DeathScreenObject;
    public GameObject RespawnPoint;
    public bool isDead=false;

    public void startDeathScreen()
    {
        DeathScreenObject.SetActive(true);
        isDead = true;
    }

    public void Update()
    {

        if (Input.GetKey(KeyCode.Return) && isDead)
        {
            Debug.Log("Respawned");
            DeathScreenObject.SetActive(false);
            isDead = false;
            this.transform.position = RespawnPoint.transform.position;
        }        
    }

    public void setRespawnPoint(GameObject newRespawnPoint)
    {
        Debug.Log("New respawn point has been set");
        this.RespawnPoint = newRespawnPoint;
    }


}
