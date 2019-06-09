using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class MainMenu : MonoBehaviour {

    public GameObject TextCanvas;

	public void StartGame()
    {
        TextCanvas.SetActive(true);
        this.gameObject.SetActive(false);
    }

    public void ExitGame()
    {
        Application.Quit();
    }

}
