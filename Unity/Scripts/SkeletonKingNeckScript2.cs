using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkeletonKingNeckScript2 : MonoBehaviour {
    public bool neckGrabbing;

    public GameObject player;

    public GameObject cam;
    public GameObject flyCam;

    public GameObject head;
    public GameObject hand;

    public GameObject unbrokenGate;
    public GameObject brokenGate;
    public GameObject brokenEntrance;

    public Vector3 startPosition;
    public Vector3 desPosition;

    public GameObject playerEndPos;
    public GameObject backGroundMusic;
    public GameObject backGroundMusicLoop;
    public GameObject playerVoice;
    public GameObject SubtitleObject;

    public AudioClip iwilladmit;
    public AudioClip backGroundClip;
    public float time;

    // Use this for initialization
    void Start () {
        player = GameObject.FindWithTag("Player");
        startPosition = this.gameObject.transform.position;
        desPosition = startPosition;
        desPosition.y += 5;
    }
	
	// Update is called once per frame
	void Update () {
        if (neckGrabbing)
        {
            cam.transform.position = hand.transform.position;
            time += Time.deltaTime;

            transform.position = Vector3.Lerp(startPosition, desPosition, time);

            if (time > 13.267f)
            {
                neckGrabbing = false;
                cam.SetActive(false);
                //player.SetActive(true);
                flyCam.SetActive(true);
                flyCam.GetComponent<HandCamFlying>().startFlying(playerEndPos);
                player.transform.position = playerEndPos.transform.position;
                StartCoroutine(DoAnimation());
                //cam.transform.parent = null;
            }
        }
	}

    public void startNeckGrab()
    {
        player.SetActive(false);
        cam.SetActive(true);
        neckGrabbing = true;
        this.GetComponent<Animator>().Play("NeckGrab");
        cam.GetComponent<LockCamera>().lockCameraOnObject(head);
        

    }

    IEnumerator DoAnimation()
    {
        yield return new WaitForSeconds(0.600f);
        brokenEntrance.GetComponent<Animator>().Play("EntranceBreak");
        brokenEntrance.GetComponent<AudioSource>().Play();
        backGroundMusic.GetComponent<AudioSource>().PlayOneShot(backGroundClip, 0.7F);

        yield return new WaitForSeconds(0.484f);
        unbrokenGate.SetActive(false);
        brokenGate.GetComponent<AudioSource>().Play();
        

        yield return new WaitForSeconds(0.916f);
        flyCam.GetComponent<AudioSource>().Play();
        
        yield return new WaitForSeconds(3.917f);
        player.SetActive(true);
        flyCam.SetActive(false);
        yield return new WaitForSeconds(2f);
        playerVoice.GetComponent<AudioSource>().PlayOneShot(iwilladmit, 0.5f);
        SubtitleObject.GetComponent<SubtitleScript>().iWillAdmit();

        this.GetComponent<ChasePlayer>().enabled = true;
        this.GetComponent<BoxCollider>().enabled = true;
        yield return new WaitForSeconds(96f);
        backGroundMusicLoop.SetActive(true);




    }
}
