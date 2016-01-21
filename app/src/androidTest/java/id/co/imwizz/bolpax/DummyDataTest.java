package id.co.imwizz.bolpax;

import android.test.AndroidTestCase;

import com.google.gson.Gson;

import id.co.imwizz.bolpax.data.entity.Profile;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by sangaji on 1/8/16.
 */
public class DummyDataTest extends AndroidTestCase {

    public void testGetDummyData() {
        String json = DummyAPI.getJson(mContext,R.raw.profile);
        Gson gson = new Gson();
        Profile profile = gson.fromJson(json, Profile.class);

        //assert test
        assertEquals("Yuri Gorbachev",profile.getName());
        assertEquals(new Integer(200000), profile.getBalance());
        assertEquals("yuri@xyz.com",profile.getEmail());
        assertEquals("+62235324982",profile.getPhone());
    }

}
