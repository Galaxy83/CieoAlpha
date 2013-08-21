package net.cieolib.cieo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.text.SpannableString;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @hide
 */
class Finish implements AnimatorListener {
	TextView target_tv, bullet;
	TextView[] bullets;
	int bullet_id, magazine_size, hit_animation, target_animation;
	int[] hit_animation_settings, target_animation_settings;
	float density_multiplier, start_position, offset;
	ViewGroup vg;
	BulletListener bl;
	SpannableString spaned_string;
	ObjectAnimator hit_animator, target_animator;
	boolean there_is_hit_animation, there_is_target_animation, is_last_bullet,
			is_first_bullet;
	HitAnimationListener hal;
	TargetAnimationListener tal;
	String direction;

	protected Finish(TextView target_tv, TextView bullet, int bullet_id,
			ViewGroup vg, BulletListener bl, int magazine_size,
			SpannableString spaned_string, TextView[] bullets,
			int hit_animation, int[] hit_animation_settings,
			int target_animation, int[] target_animation_settings,
			float density_multiplier) {
		this.target_tv = target_tv;
		this.bullet = bullet;
		this.bullet_id = bullet_id;
		this.vg = vg;
		this.bl = bl;
		this.magazine_size = magazine_size;
		this.spaned_string = spaned_string;
		this.bullets = bullets;
		this.hit_animation = hit_animation;
		this.hit_animation_settings = hit_animation_settings;
		this.target_animation = target_animation;
		this.target_animation_settings = target_animation_settings;
		this.density_multiplier = density_multiplier;
		init();
	}

	private void init() {
		there_is_hit_animation = hit_animation == 0 ? false : true;
		there_is_target_animation = target_animation == 0 ? false : true;
		is_first_bullet = bullet_id == 0 ? true : false;
		if (bullet_id == magazine_size - 1) {
			is_last_bullet = true;
			hal = new HitAnimationListener();
			tal = new TargetAnimationListener();
		}
	}

	@Override
	public void onAnimationCancel(Animator animation) {
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		bl.onBulletHit();
		if (is_first_bullet) {
			bl.onFirstBulletHit();
		} else if (is_last_bullet) {
			bl.onLastBulletHit(spaned_string);
			if (!there_is_hit_animation) {
				lastBulletAnimation();
				if (there_is_target_animation) {
					targetAnimation();
				}
			}
		}
		if (there_is_hit_animation) {
			hitAnimation();
		}
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
	}

	@Override
	public void onAnimationStart(Animator animation) {
	}

	private void lastBulletAnimation() {
		Log.d("Igor", "vg.getChildCount(): " + vg.getChildCount()
				+ " bullet_id: " + bullet_id);
		vg.removeViews(vg.getChildCount() - bullet_id - 1, bullet_id + 1);
	}

	private void shakeAnimation(boolean horizontal, int[] hit_animation_settings) {
		if (horizontal) {
			direction = "x";
			start_position = bullet.getX();
		} else {
			direction = "y";
			start_position = bullet.getY();
		}
		offset = density_multiplier * hit_animation_settings[0];
		hit_animator = ObjectAnimator.ofFloat(bullet, direction,
				start_position, start_position + offset, start_position
						- offset, start_position);
		hit_animator.setDuration(hit_animation_settings[1]);
		hit_animator.setRepeatCount(hit_animation_settings[2]);
		if (is_last_bullet)
			hit_animator.addListener(hal);
		hit_animator.start();
	}

	private void targetFlashEffect(int[] target_animation_settings) {
		target_tv.setAlpha(0f);
		target_tv.setText(spaned_string);
		target_animator = ObjectAnimator.ofFloat(target_tv, "alpha", 1f);
		target_animator.setDuration(target_animation_settings[0]);
		if (is_last_bullet) {
			target_animator.addListener(tal);
		}
		target_animator.start();
	}

	class HitAnimationListener implements AnimatorListener {
		@Override
		public void onAnimationCancel(Animator animation) {
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			bl.onHitAnimationEnd(spaned_string);
			lastBulletAnimation();
			if (there_is_target_animation)
				targetAnimation();
			else
				target_tv.setText(spaned_string);
		}

		@Override
		public void onAnimationRepeat(Animator animation) {
		}

		@Override
		public void onAnimationStart(Animator animation) {
		}
	}

	class TargetAnimationListener implements AnimatorListener {
		@Override
		public void onAnimationCancel(Animator animation) {
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			bl.onTargetAnimationEnd(spaned_string);
		}

		@Override
		public void onAnimationRepeat(Animator animation) {
		}

		@Override
		public void onAnimationStart(Animator animation) {
		}
	}

	private void targetAnimation() {
		switch (target_animation) {
		case TargetAnimation.FLASH_CUSTOM:
			targetFlashEffect(target_animation_settings == null ? new int[] { 200 }
					: target_animation_settings);

			break;
		case TargetAnimation.FLASH_SLOW:
			targetFlashEffect(new int[] { 500 });
			break;

		default:
			targetFlashEffect(new int[] { 200 });
			break;
		}
	}

	private void hitAnimation() {
		switch (hit_animation) {
		case HitAnimation.SHAKE_VERTICAL1:
			shakeAnimation(false, new int[] { 1, 70, 0 });
			break;
		case HitAnimation.SHAKE_VERTICAL0_CUSTOM:
			shakeAnimation(false, hit_animation_settings == null ? new int[] {
					1, 70, 0 } : hit_animation_settings);
			break;
		case HitAnimation.SHAKE_HORIZONTAL0_CUSTOM:
			shakeAnimation(true, hit_animation_settings == null ? new int[] {
					1, 70, 0 } : hit_animation_settings);
			break;
		default:
			shakeAnimation(true, new int[] { 1, 70, 0 });
			break;
		}
	}
}
