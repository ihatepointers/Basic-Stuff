using UnityEngine;
public class AudioSlowMoMid : MonoBehaviour
{
    public float pitchValue = 1;
    public void Update()
    {
        if (Time.timeScale < 1)
        {
            GetComponent<AudioSource>().pitch = 0.5f;
        }
        else
        {
            GetComponent<AudioSource>().pitch = pitchValue;
        }
    }
}