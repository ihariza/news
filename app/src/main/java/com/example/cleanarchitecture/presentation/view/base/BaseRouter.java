package com.example.cleanarchitecture.presentation.view.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseRouter implements BaseRouterContract {

    private AppCompatActivity activity;
    private int containerId;

    public BaseRouter(AppCompatActivity activity, int containerId) {
        this.activity = activity;
        this.containerId = containerId;
    }

    protected void show(BaseFragment fragment, boolean addToBackStack) {
        show(fragment, addToBackStack, null);
    }

    protected void show(BaseFragment fragment, boolean addToBackStack, String fragmentTag) {
        if (checkContextAvailability()) {
            String backstateName = fragment.getClass().getName();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstateName, 0);
            if (!fragmentPopped) {
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(containerId, fragment, fragmentTag);
                if (addToBackStack) {
                    fragmentTransaction.addToBackStack(backstateName);
                }
                fragmentTransaction.commit();
            }
        }
    }

    protected void remove(Fragment fragment) {
        if (checkContextAvailability() && fragment != null) {
            activity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void backView() {
        activity.getSupportFragmentManager().popBackStack();
    }

    @Override
    public void finishView() {
        activity.finish();
    }

    private boolean checkContextAvailability() {
        return !activity.isFinishing();
    }

}
