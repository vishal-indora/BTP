import random
import datetime

"""
Notes: 
**Use 24 Hour Clock for the code to work**
Don't use f-strings (not compatible with python version < 3.6)
"""

"""
PRINTED VALUES CORRESPONDING TO THE VARIABLES 

(Updated every second):
Time Elapsed -> time_elapsed
Time Remaining -> Time Remaining

(Updated every 15 minutes):
DC -> dc
SG -> sg
AG -> ag
Frequency -> frequency
Current Block Number -> current_block_number
Current Block Time -> current_block
Next Block Time -> next_block

Values under #Charges -> same as the jupyter file

Next Block's sg -> sg_plus_one
Next to Next block's sg -> sg_plus_two
and so on... -> sg_plus_three, sg_plus_four

(Updated every day)
Fuel Price -> fuel_price
"""


# Returns starting minute of the block
def function(time_m: str, blk: str = 'n') -> str:
    if blk == 'p':
        block_region = int(time_m) // 15 + 1
        op = (block_region - 1) * 15
        return str(op) * 2 if 1 == len(str(op)) else str(op)
    else:
        op = str(((int(time_m) // 15 + 1) * 15) % 60)
        return op * 2 if 1 == len(op) else op


# Takes digit in int form and converts it to two digits (if only one) by adding preceding zero, returns str
def digit_convert(number: int) -> str:
    if len(str(number)) == 1:
        return '0' + str(number)
    else:
        return str(number)


# Takes a block number and returns a number between 1 and 96 i.e. converts block 97 to block 1, etc.
def block_number(n):
    if n > 96:
        return n % 96
    else:
        return n


# Defines the seed input for random functions (for 15 minute values)
def function_seed(blk_no, day, mon):
    return (96 * 96) * blk_no + 96 * day + mon


# Defines the seed input for random functions (for 1 day values)
def function_seed_day(day, mon):
    return 32 * day + mon


"""Read time from the system clock, using that, Assign current block number, current block and next block """
t = str(datetime.datetime.now().time())
t_hh, t_mm, t_ss = t[:2], t[3:5], t[6:8]
current_block_place = int(t_mm) // 15 + 1
next_block_place = 1 if current_block_place == 4 else current_block_place + 1

current_block_number = (int(t_hh) * 60 + int(t_mm)) // 15 + 1
current_block_start = t_hh + ":" + digit_convert((int(t_mm) // 15) * 15)
next_block_start = digit_convert(((int(t_hh) + 1) % 24) if current_block_place == 4 else t_hh) + ":" + function(t_mm)
current_block_end = next_block_start
next_block_end = digit_convert((int(next_block_start[:2]) + 1) % 24) + ":" + "00" if next_block_place == 4 else digit_convert(int(next_block_start[:2])) + ":" + digit_convert(int(next_block_start[-2:]) + 15)

current_block = current_block_start + "-" + current_block_end
next_block = next_block_start + "-" + next_block_end

"""From the time (used above) find the time elapsed and the time remaining"""
time_elapsed_mm, time_elapsed_ss = digit_convert(int(t_mm) % 15), digit_convert(int(t_ss))
time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

time_elapsed = time_elapsed_mm + ":" + time_elapsed_ss
time_remaining = time_remaining_mm + ":" + time_remaining_ss

# Date calculation (used in seeding of random functions)
date = str(datetime.date.today())
date_day = int(date[-2:])
date_mon = int(date[-5:-3])

"""Randomizing the values as per the specified ranges"""
random.seed(function_seed(block_number(current_block_number), date_day, date_mon))
# Update in intervals of 15 minutes (1 time block)
dc = round(150 + random.random() * 50, 2)
sg = round(150 + random.random() * 50, 2)
ag = round(150 + random.random() * 50, 2)
frequency = round(49.5 + random.random() * 1.5, 2)
deviation = ag - sg

rupees = 0
if 49.48 <= frequency <= 50.00:
    rupees = 3.0304
elif frequency == 50.01:
    rupees = 2.5063
elif frequency == 50.02:
    rupees = 1.8798
elif frequency == 50.03:
    rupees = 1.2532
elif frequency == 50.04:
    rupees = 0.6266

# Charges
ui_dev_charge = 0
if deviation <= 0 and frequency >= 49.85:
    ui_dev_charge = round(rupees * deviation * frequency, 2)


ui_dev_charge_above_and_150 = 0
if frequency >= 49.85 and 0.12 * sg <= 150:
    if 0.85 * sg <= ag < 0.88 * sg:
        ui_dev_charge_above_and_150 = round(-1 * 50 * (-deviation - 0.12 * sg), 2)
    elif 0.8 * sg <= ag < 0.85 * sg:
        ui_dev_charge_above_and_150 = round((-1 * (100 * (-deviation - 0.15 * sg) + 1.5 * sg)) * rupees, 2)
    else:
        ui_dev_charge_above_and_150 = round((-1 * (250 * (-deviation - 0.2 * sg) + 6.5 * sg)) * rupees, 2)


ui_dev_charge_above_and_012 = 0
if frequency >= 49.85 and 0.12 * sg > 150:
    if 150 < deviation <= 200:
        ui_dev_charge_above_and_012 = round((- 1 * (50 * (- deviation - 150)) * rupees), 2)
    elif 200 < -deviation <= 250:
        ui_dev_charge_above_and_012 = round((- 1 * (100 * (-deviation - 200) + 2500) * rupees), 2)
    elif -deviation > 250:
        ui_dev_charge_above_and_012 = round((- 1 * (250 * (- deviation - 250) + 7500)) * rupees, 2)


ui_dev_charge_below_dc = 0
if ag <= sg and frequency < 49.85:
    ui_dev_charge_below_dc = round(deviation * rupees * 250, 2)

ui_dev_charge_below_dc_add = 0
if ag <= sg and frequency < 49.85:
    ui_dev_charge_below_dc_add = round(deviation * rupees * 250, 2)

oi_dev_charge = 0
if ag > sg and deviation > 0.12 * sg and 0.12 * sg < 150:
    oi_dev_charge = round(0.12 * sg * rupees * 250, 2)
elif ag > sg and deviation < sg * 0.12 and deviation < 150:
    oi_dev_charge = round(deviation * rupees * 250, 2)
elif ag > sg and 0.12 * sg > 150:
    oi_dev_charge = round(150 * rupees * 250, 2)
elif ag > sg and 0.12 * sg < 150:
    oi_dev_charge = round(0.12 * sg * rupees * 250, 2)

oi_dev_charge_add = 0
if ag > sg > 400 and frequency >= 50.1:
    oi_dev_charge_add = round(deviation * 250 * min(50.03, 0) / 100, 2)
elif ag > sg and sg <= 400 and frequency >= 50.1 and deviation > 48:
    oi_dev_charge_add = round((deviation - 48) * 250 * min(50.03, 0) / 100, 2)


sum_ui = ui_dev_charge + ui_dev_charge_above_and_150 + ui_dev_charge_above_and_012 + ui_dev_charge_below_dc + ui_dev_charge_below_dc_add
total_charge = sum_ui + oi_dev_charge + oi_dev_charge_add
total_charge_add = ui_dev_charge_above_and_150 + ui_dev_charge_above_and_012 + ui_dev_charge_below_dc_add + oi_dev_charge_add

fuel = deviation * 250 * 151.4 / 100 * (-1)
net_gain = fuel + total_charge


# SG for the next four blocks
random.seed(function_seed(block_number(current_block_number + 1), date_day, date_mon))
sg_plus_one = round(150 + random.random() * 50, 2)

random.seed(function_seed(block_number(current_block_number + 2), date_day, date_mon))
sg_plus_two = round(150 + random.random() * 50, 2)

random.seed(function_seed(block_number(current_block_number + 3), date_day, date_mon))
sg_plus_three = round(150 + random.random() * 50, 2)

random.seed(function_seed(block_number(current_block_number + 4), date_day, date_mon))
sg_plus_four = round(150 + random.random() * 50, 2)

# Updated every 24 hours
random.seed(function_seed_day(date_day, date_mon))
fuel_price = round(2 + random.random() * 8, 2)
