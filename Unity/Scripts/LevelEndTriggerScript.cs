using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelEndTriggerScript : MonoBehaviour {
    public GameObject Player;
    public GameObject LevelEndObject;
    public GameObject EndGate;
    public GameObject Blackfade;
    public GameObject EndGateDummy;
    public GameObject RealSkeleton;

    // Use this for initialization
    void Start () {
        Player = GameObject.FindWithTag("Player");
	}

    void OnTriggerEnter(Collider col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            Player.SetActive(false);
            LevelEndObject.SetActive(true);
            EndGateDummy.SetActive(false);
            EndGate.SetActive(true);
            Blackfade.SetActive(true);
            RealSkeleton.SetActive(false);
        }
    }
}
