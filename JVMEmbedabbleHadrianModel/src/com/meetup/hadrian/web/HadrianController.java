package com.meetup.hadrian.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HadrianController {
	@RequestMapping("/predict/cluster")
	public String predictModel(@RequestParam(value = "testData") String testData, @RequestParam(value = "day") String day)
			throws Exception {
		Map<String, String> filePaths = initFilePaths();
		String path = getPFADocument(filePaths, day);
		if (path == null) {
			throw new Exception("PFA Document not found");
		}
		PredictModel predict = new PredictModel(testData, path);
		String output = predict.predictModel();
		System.out.println(output);
		return "Predicted Cluster:"+output;
	}
	
	@RequestMapping("/predict/kmeans")
	public String predictKmeansCluster(@RequestParam(value = "testData") String testData)
			throws Exception {
		PredictModel predict = new PredictModel(testData, "/Users//Desktop/Hadrian/myClusterModel.pfa");
		String output = predict.predictModel();
		System.out.println(output);
		return "Predicted Cluster:"+output;
	}

	@RequestMapping("/predict/iris")
	public String predict(@RequestParam(value = "testData") String testData) {
		PredictModel predict = new PredictModel(testData, "/Users//Desktop/Hadrian/myModel.pfa");
		String output = predict.predictModel();
		System.out.println(output);
		return "Iris-"+output;
	}
	
	@RequestMapping("/predict/regression")
	public void predictRegression(@RequestParam(value = "testData") String testData) {
		PredictModel predict = new PredictModel(testData, "/Users//Desktop/Hadrian/myLinearRegModel.pfa");
		String output = predict.predictModel();
		System.out.println(output);
	}

	private Map<String, String> initFilePaths() {
		Map<String, String> filePaths = new HashMap<String, String>();
		filePaths.put("D1", "/Users//Desktop/Hadrian/myClusterModel.pfa");
		filePaths.put("D2", "/Users//Desktop/Hadrian/myClusterModelUpdated.pfa");
		return filePaths;
	}

	private String getPFADocument(Map<String, String> filePaths, String day) {
		if (filePaths.containsKey(day)) {
			return filePaths.get(day);
		}
		return null;
	}
}
