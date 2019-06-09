using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HandCamFlying : MonoBehaviour {
    public GameObject player;
    //public bool isFlying = false;
	// Use this for initialization
	void Start () {
        player = GameObject.FindWithTag("Player");
    }
	
	// Update is called once per frame
	void Update () {

	}

    public void startFlying(GameObject endPos)
    {
        player = GameObject.FindWithTag("Player");
        GetComponent<Animator>().Play("FlyToGate");
        
        //StartCoroutine(DoAnimation());
        
    }

    IEnumerator DoAnimation()
    {


        yield return new WaitForSeconds(2.317f); // wait for two seconds.


    }
}
