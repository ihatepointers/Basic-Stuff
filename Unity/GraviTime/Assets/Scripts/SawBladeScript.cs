using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SawBladeScript : MonoBehaviour {
    public GameObject FrameEnd1;
    public GameObject FrameEnd2;

    Vector3 start;
    Vector3 des;
    Vector3 tmp;

    public float fraction = 0;
    public float speed = 1f;

    public Transform from;
    public Transform to;

    public float rotationSpeed = 5;

    void Start()
    {
        start = FrameEnd1.transform.position;
        des = FrameEnd2.transform.position;

    }

    // Update is called once per frame
    void Update () {
        if (fraction < 1)
        {
            fraction += Time.deltaTime * speed;
            transform.position = Vector3.Lerp(start, des, fraction);

            transform.Rotate(rotationSpeed, 0, 0);

        }
        else
        {
            tmp = start;
            start = des;
            des = tmp;

            rotationSpeed *= -1;

            fraction = 0;
        }
    }
}
