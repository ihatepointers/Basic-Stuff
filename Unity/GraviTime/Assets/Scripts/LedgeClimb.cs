using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LedgeClimb : MonoBehaviour {
    public Vector3 origin;
    public Vector3 helperPos;
    public Vector3 upPos;
    public Vector3 forwardPos;


    public float t;
    public float climbSpeed = 5.0f;
    public float getUpSpeed = 0.3f;

    public GameObject helper;
    public GameObject LedgeClimbSound;

    public bool isLerpingUp = false;
    public bool isLerpingForward = false;

    public PlayerController playerScript;
    public camMouseLook camScript;

    public Text text;
    public Text text2;

    public AudioClip lift;

    // Use this for initialization
    void Start () {
        playerScript = GetComponent<PlayerController>();
        camScript = GetComponentInChildren<camMouseLook>();
    }
	
	// Update is called once per frame
	void Update () {
        Tick();
        
    }
    void Tick()
    {
        origin = transform.position;
        if (!isLerpingUp && !isLerpingForward && (Input.GetKey(KeyCode.LeftShift)))
        {
            RaycastHit hit1;
            RaycastHit hit2;
            Debug.DrawRay(origin, transform.forward, Color.red);
            if (Physics.Raycast(origin, transform.forward, out hit1, 2))
            {
                text.text = hit1.collider.gameObject.name;
                //Debug.Log("Origin hit: " + hit1.collider.gameObject.name);
                helperPos = helper.transform.position;
                    
                if (!Physics.Raycast(helperPos, transform.forward, out hit2, 2))
                {
                    Time.timeScale = 1;
                    LedgeClimbSound.GetComponent<AudioSource>().PlayOneShot(lift, 0.4f);
                    text2.text = "none";
                    Debug.DrawRay(helperPos, transform.forward, Color.magenta);
                    //Debug.Log("Helper hit: " + hit.collider.gameObject.name);
                    playerScript.enabled = false;
                    //camScript.enabled = false;
                    isLerpingUp = true;
                    upPos = helperPos;
                    upPos.y += 1f;

                }
                else
                {
                    text2.text = hit2.collider.gameObject.name;
                }
            }
        }
        else if (isLerpingUp)
        {

            print("Lerping up");
            t += Time.deltaTime;
            transform.position = Vector3.Lerp(transform.position, upPos, t / 2);
            if (t > 0.5)
            {
                print("Lerping up ended");
                forwardPos = transform.position + transform.forward*2;
                t = 0;
                isLerpingUp = false;
                isLerpingForward = true;
            }
        }
        else if(isLerpingForward)
        {
            print("Lerping forward");
            t += Time.deltaTime;
            transform.position = Vector3.Lerp(transform.position, forwardPos, t*4);
            if (t > 0.25)
            {
                print("Lerping forward ended");
                t = 0;
                isLerpingForward = false;
                playerScript.enabled = true;
                //camScript.enabled = true;
            }
                
        }

    }
       


 
}
