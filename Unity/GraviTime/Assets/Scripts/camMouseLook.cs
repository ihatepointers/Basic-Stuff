using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class camMouseLook : MonoBehaviour {

    public Vector2 mouseLook;
    Vector2 smoothV;
    public float sensitivity = 5.0f;
    public float smoothing = 2.0f;
    public bool wallRunning = false;
    public bool leftWallAttached = false;
    public bool rightWallAttached = false;
    public Vector3 lookAngle;

    GameObject character;
    public GameObject player;
    // Use this for initialization
    void Start () {
        character = this.transform.parent.gameObject;
        player = GameObject.FindWithTag("Player");
    }
	
	// Update is called once per frame
	void Update () {
        var md = new Vector2(Input.GetAxisRaw("Mouse X"), Input.GetAxisRaw("Mouse Y"));

        md = Vector2.Scale(md, new Vector2(sensitivity * smoothing, sensitivity * smoothing));
        smoothV.x = Mathf.Lerp(smoothV.x, md.x, 1f / smoothing);
        smoothV.y = Mathf.Lerp(smoothV.y, md.y, 1f / smoothing);
        mouseLook += smoothV;
        mouseLook.y = Mathf.Clamp(mouseLook.y, -90f, 90f);
       
        transform.localRotation = Quaternion.AngleAxis(-mouseLook.y, Vector3.right);
        //character.transform.rotation = Quaternion.AngleAxis(mouseLook.x, character.transform.up);
    }
    /*
    public void setWallRun(int side)
    {
        wallRunning = true;
        if (side == 1)
        {
            leftWallAttached = true;
        }
        else
        {
            rightWallAttached = true;
        }
    }

    public void deSetWallRun()
    {
        wallRunning = false;
        leftWallAttached = false;
        rightWallAttached = false;
        this.transform.parent = player.transform;
    }

    public void clampMouse(int side, Vector3 angle)
    {
        wallRunning = true;
        if(side == 1)
        {
            leftWallAttached = true;
        }
        else
        {
            rightWallAttached = true;
        }
        lookAngle = angle;
    }

    public void unClampMouse()
    {
        wallRunning = false;
        leftWallAttached = false;
        rightWallAttached = false;
    }
    */
}
