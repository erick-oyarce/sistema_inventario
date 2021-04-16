package com.wakeapp.inventario_offline.ui;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.FingerLogin.FingerClass;
import com.wakeapp.inventario_offline.controller.FingerLogin.FingerprintHandler;
import com.wakeapp.inventario_offline.databinding.FragmentLoginCredentialBinding;
import com.wakeapp.inventario_offline.databinding.FragmentLoginFingerBinding;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;


public class LoginFingerFragment extends Fragment {

    public LoginFingerFragment() {
        // Required empty public constructor
    }
    public static LoginFingerFragment newInstance() {
        return new LoginFingerFragment();
    }

    /** Variables para desbloqueo por huella digital **/
    KeyguardManager keyguardManager;
    FingerprintManager fingerprintManager;
    FingerClass fingerClass;

    private FragmentLoginFingerBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginFingerBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }

    public void init(){
        keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);

        /**se valida si el dispositivo cuenta con lector de huellas **/
        if (fingerprintManager == null && !fingerprintManager.isHardwareDetected()) {
            binding.finger.setVisibility(View.INVISIBLE);
        }

        binding.finger.setOnClickListener(v ->{
            huella();
        });

        binding.btnLogCred.setOnClickListener( v ->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContent, LoginCredentialFragment.newInstance())
                    .commitNow();
        });
    }

    public void huella() {
        // Check whether the device has a Fingerprint sensor.

        // Checks whether fingerprint permission is set on manifest
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            //"Fingerprint authentication permission not enabled"
        } else {
            // Check whether at least one fingerprint is registered
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                //"Register at least one fingerprint in Settings"
            } else {
                // Checks whether lock screen security is enabled or not
                if (!keyguardManager.isKeyguardSecure()) {
                    //"Lock screen security not enabled in Settings"
                } else {
                    fingerClass = new FingerClass();
                    fingerClass.generateKey();


                    if (fingerClass.cipherInit()) {
                        //          Toast.makeText(getContext(), "7", Toast.LENGTH_SHORT).show();
                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(fingerClass.getCipher());
                        FingerprintHandler helper = new FingerprintHandler(getContext());
                        helper.startAuth(fingerprintManager, cryptoObject);
                    } else {
                        //       Toast.makeText(getContext(), "8", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}