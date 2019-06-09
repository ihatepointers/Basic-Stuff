using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CheckpointSetter : MonoBehaviour {

    public GameObject Player;

    void Awake()
    {
        Player = GameObject.FindWithTag("Player");
    }

    void OnTriggerEnter(Collider col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            Debug.Log("New checkpoint reached!", this.transform.GetChild(0).gameObject);
            Player.GetComponent<DeathScreen>().setRespawnPoint(this.transform.GetChild(0).gameObject);
        }
    }
}
