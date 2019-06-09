using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RandomPitch : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Time.timeScale < 1)
        {
            GetComponent<AudioSource>().pitch = 0.5f;
        }
        else
        {
            GetComponent<AudioSource>().pitch = Random.Range(0.85f, 1.0f);
        }
    }
}
