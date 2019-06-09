using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SubtitleScript : MonoBehaviour {
    Text text;
    public GameObject SubtitleBackground;


    // Use this for initialization
    void Start () {
        text = GetComponent<Text>();
        text.text = "";
    }
	
	public void iWillAdmit()
    {

        text.text = "Alright, I will admit. I was not expecting that...";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void noTurningBack()
    {

        text.text = "Press 'Space' to jump";
        StartCoroutine(DoAnimation());
        SubtitleBackground.active = true;
    }

    public void wallRunInfo()
    {

        text.text = "Hold 'Left Shift' while jumping to run along the walls and grab ledges.";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void slowMoInfo()
    {

        text.text = "Hold 'Right Mouse Button' to slow down time";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void LedgeClimbInfo()
    {

        text.text = "Hold 'Left Shift' to climb";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void WallJumpInfo()
    {

        text.text = "Press 'Spacebar' while wallrunning to jump from walls";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void SkeletonKingSpeech()
    {
        text.color = new Color(255.0f / 255.0f, 0.0f / 255.0f, 0.0f / 255.0f);
        text.fontSize = 25;
        text.text = "INFIDEL! YOU HAVE TOUCHED THE FORBIDDEN TREASURE!";
        StartCoroutine(DoAnimationLong());
        SubtitleBackground.active = true;
    }

    public void WhatDoYouWantFromMe()
    {
        text.fontSize = 20;
        text.text = "WHAT DO YOU WANT FROM ME?";
        StartCoroutine(DoAnimationMiddle());
        SubtitleBackground.active = true;
    }

    IEnumerator DoAnimation()
    {
        yield return new WaitForSeconds(2f);
        text.text = "";
        SubtitleBackground.active = false;

    }

    IEnumerator DoAnimationMiddle()
    {
        yield return new WaitForSeconds(3f);
        text.text = "";
        SubtitleBackground.active = false;

    }

    IEnumerator DoAnimationLong()
    {
        yield return new WaitForSeconds(7f);
        text.text = "";
        SubtitleBackground.active = false;
        text.fontSize = 14;
        text.color = new Color(255.0f / 255.0f, 255.0f / 255.0f, 255.0f / 255.0f);
    }


}
