using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathCollider : MonoBehaviour {

    public GameObject Player;
    public AudioClip impaleClip;

    void Awake()
    {
        Player = GameObject.FindWithTag("Player");
    }


    void OnCollisionEnter(Collision col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            Debug.Log("deathTrigger function activated");
            GetComponent<AudioSource>().PlayOneShot(impaleClip, 1.0f);
            Player.GetComponent<DeathController>().deathTrigger();
        }
        
    }
}
