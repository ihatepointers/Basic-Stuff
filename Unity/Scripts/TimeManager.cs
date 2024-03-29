﻿using UnityEngine;

public class TimeManager : MonoBehaviour {

    public float slowdownFactor = 0.05f;
    public float slowdownLength = 2f;

    public void doSlowMotion()
    {
            Time.timeScale = slowdownFactor;
            Time.fixedDeltaTime = Time.timeScale * .02f;
    }

    public void stopSlowMotion()
    {
        Time.timeScale += (1f / slowdownLength) * Time.unscaledDeltaTime;
        Time.timeScale = Mathf.Clamp(Time.timeScale, 0f, 1f);
    }



}
