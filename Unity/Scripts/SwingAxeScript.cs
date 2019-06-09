using UnityEngine;
using System.Collections;

public class SwingAxeScript : MonoBehaviour
{

    public float torque=1;
    public float turn = 1;
    public Rigidbody rb;
    public float time;
    public float rot;

    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }

    void FixedUpdate()
    {
        rot = transform.rotation.eulerAngles.z;
        time += Time.deltaTime;
        rb.AddTorque(transform.forward * torque * turn);
        if (time > 1f)
        {
            turn = -turn;
            time = 0;
        }
    }
    
}