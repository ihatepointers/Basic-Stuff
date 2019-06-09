using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ObjectInteractController : MonoBehaviour {
    RaycastHit hit;
    GameObject cam;
    public GameObject gate;
    Text text;
    public GameObject unbrokenGate;
    public bool sealBroken = false;
    public GameObject crown;
    public GameObject skeletonKing;
    public GameObject player;
    public GameObject orbs;
    public GameObject armature;
    public GameObject altarLid;
    public AudioClip skeletonSpeech;
    public GameObject SubtitleObject;
    public GameObject altarFires;

    void Start()
    {
        cam = GameObject.FindWithTag("MainCamera");
        text = GetComponent<Text>();
    }

    private void Update()
    {
        //Debug.DrawRay(cam.transform.position, cam.transform.forward, Color.magenta, 2);
        if (Physics.Raycast(cam.transform.position, cam.transform.forward, out hit, 3)) {

            if ((hit.collider.tag == "AltarLid") && (sealBroken==false))
            {
                
                text.text = "Press E to open";
                if (Input.GetKey("e"))
                {
                    sealBroken = true;
                    hit.collider.GetComponent<Animator>().Play("AltarLidOpen");
                    hit.collider.GetComponent<AudioSource>().Play();
                    altarLid.GetComponent<AudioSource>().Play();
                    altarFires.SetActive(false);
                }
            }

            else if ((hit.collider.tag == "Crown"))
            {
                text.text = "Press E to steal the crown";
                if (Input.GetKey("e"))
                {
                    sealBroken = true;
                    crown.SetActive(false);
                    gate.GetComponent<Animator>().Play("GateShutting");
                    gate.GetComponent<AudioSource>().Play();
                    skeletonKing.GetComponent<SkeletonKingNeckScript2>().startNeckGrab();
                    orbs.SetActive(true);
                    armature.GetComponent<AudioSource>().PlayOneShot(skeletonSpeech, 0.7F);

                    StartCoroutine(DoAnimation());
                    
                }
            }

            else
            {
                text.text = "";
            }

        }
    }

    IEnumerator DoAnimation()
    {
        yield return new WaitForSeconds(4.000f);
        SubtitleObject.GetComponent<SubtitleScript>().SkeletonKingSpeech();
    }
}
