using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LockCamera : MonoBehaviour {

    public bool isLocked = false;
    public GameObject lockObject;
    // Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (isLocked)
        {
            transform.LookAt(lockObject.transform);
        }
	}

    public void lockCameraOnObject(GameObject lockedObject)
    {
        isLocked = true;
        lockObject = lockedObject;
    }
}
