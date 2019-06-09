using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkeletonKingNeckGrabScript : MonoBehaviour {
    public GameObject player;
    public bool neckGrabbing;

    public Vector3 startPosition;
    public Vector3 desPosition;
    public GameObject hand;
    public GameObject playerNeck;
    public GameObject cam;
    public GameObject skeletonKingHead;
    public float time;

    public Vector3 offset;

    private void Awake()
    {
        player = GameObject.FindWithTag("Player");
        startPosition = this.gameObject.transform.position;
        desPosition = startPosition;
        desPosition.y += 5;
        offset = player.transform.position - playerNeck.transform.position;
    }

    public void startNeckGrab()
    {
        player.transform.position = transform.position;
        playerNeck.SetActive(true);
        //player.transform.parent = this.transform;
        player.GetComponent<PlayerController>().enabled = false;
        this.GetComponent<Animator>().Play("NeckGrab");
        
        neckGrabbing = true;
        cam.GetComponent<camMouseLook>().enabled = false;
        cam.GetComponent<LockCamera>().enabled = true;
        cam.GetComponent<LockCamera>().lockCameraOnObject(skeletonKingHead);
        //StartCoroutine(DoAnimation());
    }

    public void Update()
    {
        
        if (neckGrabbing)
        {
            playerNeck.transform.position = hand.transform.position;
            player.transform.position = playerNeck.transform.position + offset;
            player.transform.LookAt(transform);
            time += Time.deltaTime;
            //transform.position = desPosition;
            transform.position = Vector3.Lerp(startPosition, desPosition, time);

            if (time > 15)
            {
                neckGrabbing = false;
                player.GetComponent<PlayerController>().enabled = true;
                cam.GetComponent<camMouseLook>().enabled = true;
                playerNeck.SetActive(false);
                player.GetComponent<Rigidbody>();
            }

        }
        
    }

    IEnumerator DoAnimation()
    {
        

        yield return new WaitForSeconds(2f); // wait for two seconds.

        neckGrabbing = true;
    }
}
