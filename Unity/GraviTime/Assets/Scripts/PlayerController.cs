using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {

    public float speed = 10.0f;
    public float gravity = 9.81f;
    public float maxVelocityChange = 10.0f;
    public bool canJump = true;
    public float jumpHeight = 2.0f;
    public float wallRunStrength = 1.0f;
    public bool grounded = false;
    public bool wallrun = false;
    public Rigidbody rb;
    public float jumpTimer = 0f;
    public float timeBetweenWallJumps = 1.0f;
    public float wallRunDistance = 10;
    public Collider coll;
    public Vector3 closestPoint;
    public Vector3 playerPos;
    public RaycastHit hit1;
    public RaycastHit hit2;
    public bool leftWallAttached = false;
    public bool rightWallAttached = false;
    public bool jumped = false;
    AudioSource playerAudio;
    public AudioSource slowMoSound;
    GameObject cam;
    public float slowdownFactor = 0.3f;
    public float slowdownLength = 2f;
    public bool cancelSlowTime = false;
    public float airMovingValue = 0.02f;
    public float airMovement = 0.02f;
    public Quaternion rotation = Quaternion.identity;
    public bool isRunning=false;
    public bool isRunningLeft = false;
    public bool isRunningRight = false;
    public bool animationJumped = false;
    private Animator animator;
    public camMouseLook camScript;
    public Vector3 pushVector = new Vector3();
    public Vector3 localVelocity = new Vector3();
    public Vector3 rigidBodyVelocity = new Vector3();
    public Vector3 wallRunVelocity = new Vector3();
    public HeadBobber headBobberScript;
    public GameObject Footsteps;
    public GameObject JumpSound;
    public GameObject WallJump;
    
    public AudioClip jump;
    public AudioClip walljump;

    public float wallRunSpeedClampUp;
    public float wallRunSpeedClampDown;

    public bool alreadyWallRunning;


    void Awake()
    {
        rb = GetComponent<Rigidbody>();
        rb.freezeRotation = true;
        rb.useGravity = false;
        Cursor.lockState = CursorLockMode.Locked;
        coll = GetComponent<Collider>();
        cam = GameObject.FindWithTag("MainCamera");
        playerAudio = GetComponent<AudioSource>();
        slowMoSound = playerAudio;
        animator = GetComponentInChildren<Animator>();
        
    }

    void Update()
    {
        rigidBodyVelocity = rb.velocity;

        //animation
        if (Input.GetKey("a") && grounded)
        {

            animator.SetBool("isRunning", false);
            animator.SetBool("isRunningLeft", true);
            animator.SetBool("isRunningRight", false);
            isRunning = false;
            isRunningLeft = true;
            isRunningRight = false;
        }
        else if (Input.GetKey("d") && grounded)
        {
            animator.SetBool("isRunning", false);
            animator.SetBool("isRunningLeft", false);
            animator.SetBool("isRunningRight", true);
            isRunning = false;
            isRunningLeft = false;
            isRunningRight = true;
        }

        else if (Input.GetKey("w") && grounded)
        {
            
            //print(rb.velocity.magnitude);
            isRunning = true;
            isRunningLeft = false;
            isRunningRight = false;
            animator.SetBool("isRunning", true);
            animator.SetBool("isRunningLeft", false);
            animator.SetBool("isRunningRight", false);
        }
        else
        {
            isRunning = false;
            isRunningLeft = false;
            isRunningRight = false;
            animator.SetBool("isRunning", false);
            animator.SetBool("isRunningLeft", false);
            animator.SetBool("isRunningRight", false);
        }

        if((Input.GetKey("space") && grounded))
        {
            animationJumped = true;

            animator.SetBool("jumped", true);
        }
        if(animationJumped && grounded)
        {
            animationJumped = false;
            animator.SetBool("jumped", false);
        }

        if (wallrun && leftWallAttached)
        {
            animator.SetBool("wallrunLeft", true);
        }
        else
        {
            animator.SetBool("wallrunLeft", false);
        }

        if (wallrun && rightWallAttached)
        {
            animator.SetBool("wallrunRight", true);
        }
        else
        {
            animator.SetBool("wallrunRight", false);
        }


        //slowmo
        if (Input.GetButtonDown("Fire2"))
        {
            Time.timeScale = slowdownFactor;
            Time.fixedDeltaTime = Time.timeScale * 0.02f;
            cam.GetComponent<HeadBobber>().disableBobbing();
            playerAudio.Play();
        }

        if (Input.GetButtonUp("Fire2"))
        {
            Time.timeScale = 1;
            Time.fixedDeltaTime = Time.timeScale * .02f;
            cam.GetComponent<HeadBobber>().enableBobbing();
        }

        if (!wallrun)
        {
            //camScript.deSetWallRun();
        }


        playerPos = rb.transform.position;
        jumpTimer += Time.deltaTime;
        // Calculate how fast we should be moving
        Vector3 targetVelocity = new Vector3(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));

        //footstep sounds
        if((grounded || wallrun ) && (targetVelocity != Vector3.zero))
        {
            if (wallrun)
            {
                if (Input.GetKey(KeyCode.LeftShift))
                {
                    Footsteps.SetActive(true);
                }
                else
                {
                    Footsteps.SetActive(false);
                }
            }
            else if(grounded)
            {
                Footsteps.SetActive(true);
            }
            
        }
        else
        {
            Footsteps.SetActive(false);
        }




        targetVelocity = transform.TransformDirection(targetVelocity);
        targetVelocity *= speed;


        // Apply a force that attempts to reach our target velocity
        Vector3 velocity = rb.velocity;
        Vector3 velocityChange = (targetVelocity - velocity);
        if (!wallrun)
        {
            
            if (grounded)
            {
                velocityChange.x = Mathf.Clamp(velocityChange.x, -maxVelocityChange, maxVelocityChange);
                velocityChange.z = Mathf.Clamp(velocityChange.z, -maxVelocityChange, maxVelocityChange);
                velocityChange.y = 0;
                rb.AddForce(velocityChange, ForceMode.VelocityChange);
            }
            else
            {
                velocityChange.x = Mathf.Clamp(velocityChange.x, -maxVelocityChange, maxVelocityChange) * airMovement;
                velocityChange.z = Mathf.Clamp(velocityChange.z, -maxVelocityChange, maxVelocityChange) * airMovement;
                velocityChange.y = 0;
                rb.AddForce(velocityChange, ForceMode.VelocityChange);
            }

        }

        else
        {
            if (!grounded)
            {
                /*
                if(Input.GetKeyDown("s")){
                    GetComponent<Rigidbody>().velocity = GetComponent<Rigidbody>().velocity - new Vector3(0.1f, 0.1f, 1);
                
                velocityChange.x = Mathf.Clamp(velocityChange.x, -maxVelocityChange, maxVelocityChange) ;
                velocityChange.z = Mathf.Clamp(velocityChange.z, -maxVelocityChange, maxVelocityChange) ;
                velocityChange.y = 0;
                rb.AddForce(velocityChange, ForceMode.VelocityChange);
                */
                Debug.DrawRay(rb.position, hit1.point, Color.magenta, 2);
                //transform.rotation = Quaternion.LookRotation(hit1.normal, Vector3.up);
                if (leftWallAttached)
                {
                    //transform.rotation *= Quaternion.Euler(0f, -90f, 0f);
                    //camScript.setWallRun(1);
                    if (Input.GetKey("w"))
                    {
                        pushVector = Quaternion.Euler(0, -90, 0) * hit1.normal;
                        rb.AddForce(pushVector * 10);
                    }

                }
                else if (rightWallAttached)
                {
                    if (Input.GetKey("w"))
                    {
                        pushVector = Quaternion.Euler(0, 90, 0) * hit1.normal;
                        rb.AddForce(pushVector * 10);
                    }
                    //transform.rotation *= Quaternion.Euler(0f, 90f, 0f);
                    //camScript.setWallRun(2);
                }
                
            }
            else
            {
                velocityChange.x = Mathf.Clamp(velocityChange.x, -maxVelocityChange, maxVelocityChange);
                velocityChange.z = Mathf.Clamp(velocityChange.z, -maxVelocityChange, maxVelocityChange);
                velocityChange.y = 0;
                rb.AddForce(velocityChange, ForceMode.VelocityChange);

            }
        }
        
        /*

        localVelocity = transform.InverseTransformDirection(rb.velocity);
        localVelocity.x = Mathf.Clamp(localVelocity.x, -7f, 7f);
        localVelocity.z = Mathf.Clamp(localVelocity.z, -7f, 7f);
        */

        //Debug.DrawRay(rb.transform.position, transform.right, Color.green);
        //Debug.DrawRay(rb.transform.position, -transform.right, Color.red);



        //wallrun
        /*
        if (Physics.Raycast(rb.transform.position, -transform.right,out hit1,1) && !grounded)
        {
            
            cam.transform.rotation *=Quaternion.Euler(0, 0, -5f);
            leftWallAttached = true;
            rb.AddForce(transform.up * wallRunStrength);
            
        }
        else
        {
            leftWallAttached = false;
        }

        if (Physics.Raycast(rb.transform.position, transform.right, 1) && !grounded)
        {
            
            cam.transform.rotation *= Quaternion.Euler(0f, 0f, 5f);
            rightWallAttached = true;
            rb.AddForce(transform.up * wallRunStrength); 
           
        }
        else
        {
            rightWallAttached = false;
        }
        */
        //print(cam.transform.forward);
        /*
        if (!(Physics.Raycast(rb.transform.position, -transform.right, out hit1, 2)))
        {
            leftWallAttached = false;
            wallrun = false;
            
        }else if(Physics.Raycast(rb.transform.position, transform.right, out hit1, 2))
        {
            rightWallAttached = false;
            wallrun = false;
        }
        */


        //wallrun
        if (Input.GetKey(KeyCode.LeftShift))
        {
            if (!grounded && wallrun)
            {
                /*
                if ((Physics.Raycast(rb.transform.position, -transform.right, out hit1, 1)) || (Physics.Raycast(rb.transform.position, transform.right, out hit1, 1)))
                {
                    rb.AddForce(-hit1.normal * 1000);
                }
                */
                Debug.DrawRay(rb.transform.position, hit1.normal, Color.red, 10);
                //rb.velocity = new Vector3(rb.velocity.x, 0, rb.velocity.z);
                if (leftWallAttached)
                {
                    print("clamped!");
                    print("cam rotated right");
                    //cam.GetComponent<camMouseLook>().clampMouse(1,hit1.normal);
                    //print(hit1.normal);
                    //print(rotation.eulerAngles.z);


                    //cam.transform.rotation *= Quaternion.Euler(0, 0, -10f);
                    cam.GetComponent<SimpleSmoothMouseLook>().tiltLeft();
                    rb.AddForce(rb.transform.up * 20);

                    //wallrunning slowdrop
                    //localVelocity.y = Mathf.Clamp(rb.velocity.y, wallRunSpeedClampDown, wallRunSpeedClampUp);

                    if (!alreadyWallRunning)
                    {
                        
                    }
                    wallRunVelocity = rb.velocity;
                    print("Wallrun velocity.y before clamped: " + wallRunVelocity.y);
                    wallRunVelocity.y = Mathf.Clamp(wallRunVelocity.y, wallRunSpeedClampDown, wallRunSpeedClampUp);
                    print("Wallrun velocity.y after clamped: " + wallRunVelocity.y);
                    rb.velocity = new Vector3(rb.velocity.x, wallRunVelocity.y, rb.velocity.z);
                    alreadyWallRunning = true;

                    //print(localVelocity.y);
                    //rb.velocity = transform.TransformDirection(localVelocity);
                }
                else if (rightWallAttached)
                {
                    print("clamped!");
                    print("cam rotated left");
                    //cam.GetComponent<camMouseLook>().clampMouse(2,hit1.normal.x);
                    //print(hit1.normal.x);

                    //cam.transform.rotation *= Quaternion.Euler(0, 0, 10f);
                    cam.GetComponent<SimpleSmoothMouseLook>().tiltRight();
                    rb.AddForce(rb.transform.up * 20);

                    //wallrunning slowdrop
                    //localVelocity.y = Mathf.Clamp(rb.velocity.y, wallRunSpeedClampDown, wallRunSpeedClampUp);
                    if (!alreadyWallRunning)
                    {
                        
                    }
                    wallRunVelocity = rb.velocity;
                    print("Wallrun velocity.y before clamped: " + wallRunVelocity.y);
                    wallRunVelocity.y = Mathf.Clamp(wallRunVelocity.y, wallRunSpeedClampDown, wallRunSpeedClampUp);
                    print("Wallrun velocity.y after clamped: " + wallRunVelocity.y);
                    rb.velocity = new Vector3(rb.velocity.x, wallRunVelocity.y, rb.velocity.z);
                    alreadyWallRunning = true;

                    //print(localVelocity.y);
                    //rb.velocity = transform.TransformDirection(localVelocity);
                }




                Debug.DrawRay(rb.transform.position, transform.up, Color.blue, 3);
            }

            else if (!grounded && !wallrun)
            {
                //setAttachedWalls();

            }
        }

        if (grounded)
        {
            alreadyWallRunning = false;
        }
        

        


        // Jump
        
        if(canJump && Input.GetKeyDown(KeyCode.Space)){
            animationJumped = true;
            alreadyWallRunning = false;
            animator.SetBool("jumped", true);
            animator.SetBool("grounded", false);
            if (grounded)
            {
                JumpSound.GetComponent<AudioSource>().PlayOneShot(jump, 0.7f);
                grounded = false;
                //no wall run jump
                if (FindSide() == -1)
                {
                    rb.velocity = new Vector3(velocity.x, CalculateJumpVerticalSpeed(), velocity.z);
                    rb.AddForce(rb.transform.forward * 300);
                    jumped = true;
                    wallrun = false;
                }
                //start near wallrun
                else
                {
                    print("near wall run start");
                    if (Physics.Raycast(rb.transform.position, -transform.right, out hit1, 1))
                    {
                        leftWallAttached = true;
                        rightWallAttached = false;
                        wallrun = true;
                        rb.velocity = new Vector3(velocity.x, CalculateJumpVerticalSpeed(), velocity.z);
                    }
                    else if (Physics.Raycast(rb.transform.position, transform.right, out hit1, 1))
                    {
                        leftWallAttached = false;
                        rightWallAttached = true;
                        wallrun = true;
                        rb.velocity = new Vector3(velocity.x, CalculateJumpVerticalSpeed(), velocity.z);
                    }
                    airMovement = 0;
                }
                

            }
            //walljump
            else
            {
                //WallJump.GetComponent<AudioSource>().PlayOneShot(walljump, 0.7f);

                print("unclamped!");
                cam.GetComponent<SimpleSmoothMouseLook>().unTilt();
                localVelocity.y = Mathf.Clamp(localVelocity.y, -100f, 100f);
                if (leftWallAttached)
                {
                    JumpSound.GetComponent<AudioSource>().PlayOneShot(jump, 0.7f);
                    leftWallAttached = false;
                    rightWallAttached = false;
                    wallrun = false;
                    rb.velocity = CalculateWallJumpSpeed();
                    //rb.AddForce(rb.transform.up * 800);
                    //rb.AddForce(hit1.normal * 700);
                    //rb.AddForce(cam.transform.rotation.eulerAngles * 3);

                }
                else if (rightWallAttached)
                {
                    JumpSound.GetComponent<AudioSource>().PlayOneShot(jump, 0.7f);
                    leftWallAttached = false;
                    rightWallAttached = false;
                    wallrun = false;
                    rb.velocity = CalculateWallJumpSpeed();
                    //rb.AddForce(rb.transform.up * 800);
                    //rb.AddForce(hit1.normal * 700);
                    //rb.AddForce(cam.transform.rotation.eulerAngles * 1);
                }
                
            }
        }
        
       
        
        

        if (Input.GetKey("escape"))
        {
        Cursor.lockState = CursorLockMode.None;
        }
        

        // We apply gravity manually for more tuning control
        rb.AddForce(new Vector3(0, -gravity * rb.mass, 0));

        
    }

    void OnCollisionStay(Collision collision)
    {


        
        if (collision.gameObject.tag == "Enviroment")
        {
            if (!grounded)
            {
                if(leftWallAttached || rightWallAttached)
                {
                    rb.AddForce(-hit1.normal * 100);
                }
            }
        }
        

        jumped = false;
            
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag == "Enviroment")
        {
            if (!grounded)
            {
                wallrun = true;
                airMovement = 0;

                if (FindSide() == 0)
                {
                    leftWallAttached = true;
                    rightWallAttached = false;
                    print("Left wall attached!");

                }
                else if (FindSide() == 1)
                {
                    leftWallAttached = false;
                    rightWallAttached = true;
                    print("Right wall attached!");
                }

            }

            
            //Debug.DrawRay(collision.contacts[0].point, rb.transform.position, Color.blue, 0, true);
            //Debug.DrawRay(collision.transform.position, rb.transform.position, Color.red, 0, true);
            //Debug.DrawRay(rb.transform.position, findWall(), Color.red, 10, true);
        }else if (collision.gameObject.tag == "Ground")
            {
            print("collision ground entered");
                wallrun = false;
                leftWallAttached = false;
                rightWallAttached = false;
                airMovement = airMovingValue;
                grounded = true;
                animator.SetBool("grounded", true);
            print("grounded true");
            cam.GetComponent<SimpleSmoothMouseLook>().unTilt();
            
            }



    }

    private void OnCollisionExit(Collision collision)
    {
        if (collision.gameObject.tag == "Enviroment")
        {
            if(Find3Side() == false)
            {
                
                
                wallrun = false;
                leftWallAttached = false;
                rightWallAttached = false;
                
                print("collision exit");
                //airMovement = 0.02f;
                
            }
            //cam.GetComponent<camMouseLook>().unClampMouse();
        }

    }

    float CalculateJumpVerticalSpeed()
    {
        // From the jump height and gravity we deduce the upwards speed 
        // for the character to reach at the apex.
        return Mathf.Sqrt(2 * jumpHeight * gravity);
    }

    Vector3 CalculateWallJumpSpeed()
    {
        Vector3 wallJump = new Vector3();
        wallJump.x = rb.velocity.x + hit1.normal.x * 10 + cam.transform.forward.x * 5;
        wallJump.y = Mathf.Sqrt(jumpHeight * gravity );
        wallJump.z = rb.velocity.z + hit1.normal.z * 10 + cam.transform.forward.z * 5;
        
        return wallJump;
        
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("Ground"))
        {
            grounded = true;
            print("grounded true trigger");
        }

    }

    //findWall
    Vector3 findWall()
    {
        Vector3 wallDirection = new Vector3(0, 0, 0);
        if (Physics.Raycast(rb.transform.position, -transform.right, out hit1, 2) && !grounded)
        {
            rb.AddForce(hit1.normal * 100);
            float angle = Vector3.Angle(hit1.normal, rb.position);
            Debug.DrawRay(rb.position, hit1.point, Color.blue,2);
            if (angle != 90)
            {
                float difference = angle - 90;
                wallDirection = transform.position;
                Vector3 diff = new Vector3(10, difference, 0);
                wallDirection += diff;
                //Debug.DrawRay(rb.position, hit1.point, Color.red,2);
            }
        }
        return wallDirection;
    }

    //findSide
    public int FindSide()
    {
        if (Physics.Raycast(rb.transform.position, -transform.right, out hit1, 1))
        {

            return 0;
            
        }
        else if(Physics.Raycast(rb.transform.position, transform.right, out hit1, 1))
        {

            return 1;
            
        }
        else
        {
            return -1;
        }
    }

    public void setAttachedWalls()
    {
        if (Physics.Raycast(rb.transform.position, -transform.right, out hit1, 1))
        {

            leftWallAttached = true;
            rightWallAttached = false;
            wallrun = true;
        }
        else if (Physics.Raycast(rb.transform.position, transform.right, out hit1, 1))
        {

            leftWallAttached = false;
            rightWallAttached = true;
            wallrun = true;

        }
        else
        {
            leftWallAttached = false;
            rightWallAttached = false;
            wallrun = false;
        }
    }

    public bool Find3Side()
    {
        if ((Physics.Raycast(rb.transform.position, -transform.right, out hit1, 1) || 
            (Physics.Raycast(rb.transform.position, transform.right, out hit1, 1)) ||
             (Physics.Raycast(rb.transform.position, -transform.forward, out hit1, 1))))
        {

            return true;

        }
        else
        {
            return false;
        }
    }


    public void disableGravity()
    {
        gravity = 0;
    }

    public void enableGravity()
    {
        gravity = 30f;
    }




}
