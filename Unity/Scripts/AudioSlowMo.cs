using UnityEngine;
public class AudioSlowMo : MonoBehaviour
{
    public void Update()
    {
        if(Time.timeScale < 1)
        {
            GetComponent<AudioSource>().pitch = 0.2f;
        }
        else
        {
            this.GetComponent<AudioSource>().pitch = Random.Range(0.75f, 1.15f);
        }
        
    }
}