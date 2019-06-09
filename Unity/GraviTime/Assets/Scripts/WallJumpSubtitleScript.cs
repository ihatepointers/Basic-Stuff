﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WallJumpSubtitleScript : MonoBehaviour {

    public GameObject Player;
    public GameObject SubtitleObject;



    void OnTriggerEnter(Collider col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            SubtitleObject.GetComponent<SubtitleScript>().WallJumpInfo();
        }
    }
}
