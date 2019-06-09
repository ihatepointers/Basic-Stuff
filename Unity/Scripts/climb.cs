using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class climb : MonoBehaviour {
    public Rigidbody rb;

    RaycastHit hit;
    void Awake()
    {
        rb = GetComponent<Rigidbody>();
    }

    private void Update()
    {
       if(Physics.Raycast(transform.position, transform.forward, out hit, 1f,1))
        {
            Debug.DrawRay(transform.position, hit.point, Color.red, 2);
            Vector3 upPoint = hit.transform.up * 2;
            Debug.DrawRay(hit.point, upPoint, Color.blue, 2);
            Vector3 forwardPoint = (hit.transform.forward * 2);
            Debug.DrawRay(upPoint, forwardPoint, Color.magenta, 2);
            

        }  
    }
    
	
}
