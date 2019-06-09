using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Trigger : MonoBehaviour {
    public GameObject gate;
    AudioSource audioSource;
    public Animator anim;

    void Awake()
    {
        audioSource = GetComponentInParent<AudioSource>();
        anim = this.GetComponentInParent<Animator>();

    }

    private void OnTriggerEnter()
    {
        anim.Play("GateShutting");
        audioSource.Play();
    }
}
