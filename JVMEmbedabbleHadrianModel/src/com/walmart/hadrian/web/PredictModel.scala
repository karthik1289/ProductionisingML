package com.walmart.hadrian.web

import com.opendatagroup.hadrian.jvmcompiler.PFAEngine;

class PredictModel(val testData: String, val pfaFilePath: String) {

  def predictModel():String = {
    val engine = PFAEngine.fromJson(new java.io.File(pfaFilePath)).head;
    val inputData = engine.jsonInput(testData);
    engine.begin();
    engine.jsonOutput(engine.action(inputData));
  }

}