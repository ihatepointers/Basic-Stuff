using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlatformController : MonoBehaviour {

    private GameObject player;
    public Rigidbody rb;
    public Vector3 previous;

    // Use this for initialization
    void Start () {
        player = GameObject.FindWithTag("Player");
        //rb = player.GetComponent<Rigidbody>();
    }
	
	// Update is called once per frame
	void Update () { 
        //previous = this.transform.position;
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("Player"))
        {
            //var velocity = (this.transform.position - previous) / Time.deltaTime;
            player.transform.parent = transform.parent;
            //rb.velocity -= velocity;
        }

    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.CompareTag("Player"))
        {
            player.transform.parent = null;
        }
    }
}
