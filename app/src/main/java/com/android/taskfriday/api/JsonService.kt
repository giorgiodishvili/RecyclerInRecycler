package com.android.taskfriday.api

import android.util.Log
import com.android.taskfriday.model.InputFieldModel
import org.json.JSONArray


class JsonService {
    private val jsonString: String = "[\n" +
            "[\n" +
            "{\n" +
            "\"field_id\": 1,\n" +
            "\"hint\": \"UserName\",\n" +
            "\"field_type\": \"input\",\n" +
            "\"keyboard\": \"text\",\n" +
            "\"required\": false,\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "},\n" +
            "{\n" +
            "\"field_id\": 2,\n" +
            "\"hint\": \"Email\",\n" +
            "\"field_type\": \"input\",\n" +
            "\"required\": true,\n" +
            "\"keyboard\": \"text\",\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "},\n" +
            "{\n" +
            "\"field_id\": 3,\n" +
            "\"hint\": \"phone\",\n" +
            "\"field_type\": \"input\",\n" +
            "\"required\": true,\n" +
            "\"keyboard\": \"number\",\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "}\n" +
            "],\n" +
            "[\n" +
            "{\n" +
            "\"field_id\": 4,\n" +
            "\"hint\": \"Full Name\",\n" +
            "\"field_type\": \"input\",\n" +
            "\"keyboard\": \"text\",\n" +
            "\"required\": true,\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "},\n" +
            "{\n" +
            "\"field_id\": 14,\n" +
            "\"hint\": \"Jemali\",\n" +
            "\"field_type\": \"input\",\n" +
            "\"keyboard\": \"text\",\n" +
            "\"required\": false,\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "},\n" +
            "{\n" +
            "\"field_id\": 89,\n" +
            "\"hint\": \"Birthday\",\n" +
            "\"field_type\": \"chooser\",\n" +
            "\"required\": false,\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "},\n" +
            "{\n" +
            "\"field_id\": 898,\n" +
            "\"hint\": \"Gender\",\n" +
            "\"field_type\": \"chooser\",\n" +
            "\"required\": false,\n" +
            "\"is_active\": true,\n" +
            "\"icon\": \"https://jemala.png\"\n" +
            "}\n" +
            "]\n" +
            "]"

    private val inputFieldArray: MutableList<MutableMap<Int,InputFieldModel>> = mutableListOf()

    fun parseJson(){
        val jsonArray = JSONArray(jsonString)
        val dataMap = mutableMapOf<Int, InputFieldModel>()
        lateinit var inputModel: InputFieldModel
        for(i in 0 until jsonArray.length()){
            dataMap.clear()
            for(j in 0 until jsonArray.getJSONArray(i).length()) {
                val get = jsonArray.getJSONArray(i).getJSONObject(j)
                Log.i("inputFieldArray", (get).toString())


                val fieldID = get.getInt("field_id")
                val hint = get.getString("hint")
                val fldType = get.getString("field_type")
                val required = get.getString("required").toBooleanStrictOrNull() ?: false
                val isActive = get.getString("is_active").toBooleanStrictOrNull() ?: false
                val icon = get.getString("icon")
                inputModel = if(get.has("keyboard")){
                    val keyboard = get.getString("keyboard")
                    InputFieldModel(fieldID,hint,fldType,keyboard,required,isActive,icon)
                }else{
                    InputFieldModel(fieldID,hint,fldType,"",required,isActive,icon)
                }
                dataMap[inputModel.field_id] = inputModel
            }
            inputFieldArray.add(dataMap)
        }

//        Log.i("JSONARRAY",jsonArray.getString(1))
    }

    fun listToMap(list: MutableList<InputFieldModel>){
        val map = list.associateBy({it.field_id}, {it})

    }

}