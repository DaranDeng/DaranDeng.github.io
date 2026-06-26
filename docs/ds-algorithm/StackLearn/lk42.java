package StackLearn;
import java.util.Stack;
public class lk42 {
        public int trap(int[] height) {
            Stack<Integer> stack=new Stack<>();
            stack.push(0);
            int sum=0;
            int mid;
            for(int i=0;i<height.length;i++){
                int h=height[i];
                if(h<=height[stack.peek()])stack.push(i);
                while(!stack.isEmpty()&&h>height[stack.peek()]){
                    mid=stack.pop();

                    if(stack.isEmpty())break;
                    int h2=Math.min(height[stack.peek()],h)-height[mid];
                    int w=i-stack.peek()-1;
                    sum+=h2*w;

                }
                stack.push(i);
            }
            return sum;
        }
    }
