package io.wispforest.gadget.util;

import io.wispforest.owo.ui.core.Component;

import java.util.Stack;

public class CustomScissorStack {
    private static final Stack<ScissorState> SCISSOR_STACK = new Stack<>();
    
    public static void push(int x, int y, int width, int height) {
        SCISSOR_STACK.push(new ScissorState(x, y, width, height));
    }
    
    public static void pop() {
        if (!SCISSOR_STACK.isEmpty()) {
            SCISSOR_STACK.pop();
        }
    }
    
    public static boolean isVisible(Component component) {
        if (SCISSOR_STACK.isEmpty()) return true;
        
        ScissorState current = SCISSOR_STACK.peek();
        return component.x() < current.x + current.width &&
               component.x() + component.width() > current.x &&
               component.y() < current.y + current.height &&
               component.y() + component.height() > current.y;
    }
    
    private record ScissorState(int x, int y, int width, int height) {}
}