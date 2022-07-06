package com.example.newsapppp.presentation.usecase

import android.os.Bundle

class AdapterClickSandBundleUseCase {

  fun execute(){
          val bundle = Bundle().apply {
              putParcelable("article", this)
          }
  }
}