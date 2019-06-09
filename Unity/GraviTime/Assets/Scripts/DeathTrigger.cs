using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathTrigger : MonoBehaviour {

    public GameObject Player;
    public AudioClip impaleClip;

    void Awake()
    {
        Player = GameObject.FindWithTag("Player");
    }


    void OnTriggerEnter(Collider col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            Debug.Log("deathTrigger function activated");
            GetComponent<AudioSource>().PlayOneShot(impaleClip, 1.0f);
            Player.GetComponent<DeathController>().deathTrigger();

        }
    }
}
