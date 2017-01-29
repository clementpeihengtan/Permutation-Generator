package com.example.clementtan.permutation_gen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button generate;
    private EditText input;
    private TextView result;
    String str;

    public void generateallper(String stringy){
        int [] nums = convertoarray(stringy);
//        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
        List<List<Integer>> res = permutation(nums);
        String allper = strconversion(res);
        populateallper(allper);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generate = (Button)findViewById(R.id.button);
        result = (TextView)findViewById(R.id.textView);
        generate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                input = (EditText)findViewById((R.id.editText));
                if ((input.getText().toString()).equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter sequence of Integer", Toast.LENGTH_LONG).show();
                    return;
                }
                str = input.getText().toString();
                generateallper(str);
            }
        });
    }

    public int [] convertoarray(String s){

        String [] sg = s.split(" ");

        int [] number = new int[sg.length];
        for (int i = 0; i < sg.length; i++) {
            number[i] = (int) Long.parseLong(sg[i]);
        }
        return number;
    }

    public List<List<Integer>> permutation(int[] nums) {
        ArrayList<List<Integer>> permutations
                = new ArrayList<>();
        if (nums == null) {

            return permutations;
        }

        if (nums.length == 0) {
            permutations.add(new ArrayList<Integer>());
            return permutations;
        }

        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<>();

        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }

            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }
            permutations.add(permutation);
        }

        return permutations;
    }
    //    public List<List<Integer>> permutation(int [] nums){
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);
//        List<Integer> temp = new ArrayList<>();
//        helper(res, temp, nums);
//        return res;
//    }
//
//    public void helper(List<List<Integer>> res, List<Integer> temp, int [] nums){
//        if(temp.size() == nums.length){
//            res.add(new ArrayList<>(temp));
//            return;
//        }else{
//            for(int i = 0 ; i < nums.length; i++){
//                if(temp.contains(nums[i])) continue;
//                temp.add(nums[i]);
//                helper(res, temp, nums);
//                temp.remove(temp.size()-1);
//            }
//        }
//    }
    public String strconversion(List<List<Integer>> res){
        String allper = "";
        for(int i = 0 ; i < res.size() ;i++){
            String per = "[";
            for(int j = 0; j < res.get(0).size() ; j++){
                per+=Integer.toString(res.get(i).get(j));
                per+=",";
            }
            per+="]";
            allper+= per+" ";

        }
//        Toast.makeText(this, allper, Toast.LENGTH_SHORT).show();
        return allper;
    }
    public void populateallper(String s){
        result.setText(s);
    }



}